package br.com.thoughtworks.controller;

import br.com.thoughtworks.controller.DTO.TopicoDTO;
import br.com.thoughtworks.controller.form.TopicForm;
import br.com.thoughtworks.controller.respository.CourseRepository;
import br.com.thoughtworks.model.Topico;
import br.com.thoughtworks.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicoDTO> getTopics(String courseName) {
        if(courseName == null) {
            return TopicoDTO.parseTopic(topicRepository.findAll());
        }
        return TopicoDTO.parseTopic(topicRepository.findByCursoNome(courseName));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> postTopic(@RequestBody TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        Topico topic = topicForm.parseTopic(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topic));
    }
}
