package br.com.thoughtworks.controller.respository;

import br.com.thoughtworks.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nomeCurso);
}
