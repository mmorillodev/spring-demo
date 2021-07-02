package br.com.thoughtworks.repository;

import br.com.thoughtworks.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNome(String courseName, Pageable pageable);

    @Query("SELECT topic FROM Topico topic WHERE topic.curso.nome = :courseName")
    List<Topico> customFindByCursoNome(@Param("courseName") String nomeCurso);
}
