package br.com.thoughtworks.controller;

import br.com.thoughtworks.controller.DTO.TopicDetailsDTO;
import br.com.thoughtworks.controller.DTO.TopicoDTO;
import br.com.thoughtworks.controller.form.TopicForm;
import br.com.thoughtworks.controller.form.TopicUpdateForm;
import br.com.thoughtworks.controller.respository.CourseRepository;
import br.com.thoughtworks.model.Topico;
import br.com.thoughtworks.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Not a good practice to use cache in this case since it's very likely that this entity's going to have a big amount
    // of new insertions and updates, which leads us to create and delete cache very often which could be harmful to our
    // performance. This was an example purely for academic purposes
    @GetMapping
    @Cacheable(value = "topicsList")
    public Page<TopicoDTO> getTopics(@RequestParam(required = false) String courseName,
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, size = 50) Pageable pageable) {

        if(courseName == null) {
            return TopicoDTO.parseTopic(topicRepository.findAll(pageable));
        }
        return TopicoDTO.parseTopic(topicRepository.findByCursoNome(courseName, pageable));
    }

    @PostMapping
    @Transactional
    @CacheEvict(cacheNames = "topicsList", allEntries = true)
    public ResponseEntity<TopicoDTO> postTopic(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        Topico topic = topicRepository.save(topicForm.parseTopic(courseRepository));

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDTO> getDetailedTopic(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicRepository.findById(id);
        return topicoOptional.map(topico ->
                ResponseEntity.ok(new TopicDetailsDTO(topico))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(cacheNames = "topicsList", allEntries = true)
    public ResponseEntity<TopicoDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm topicForm) {
        Optional<Topico> topicoOptional = topicRepository.findById(id);
        return topicoOptional.map(topico -> {
            Topico topic1 = topicForm.update(id, topicRepository);
            return ResponseEntity.ok(new TopicoDTO(topic1));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(cacheNames = "topicsList", allEntries = true)
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicRepository.findById(id);
        return topicoOptional.map(topic -> {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
