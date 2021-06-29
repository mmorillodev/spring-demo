package br.com.thoughtworks.controller.form;

import br.com.thoughtworks.controller.respository.CourseRepository;
import br.com.thoughtworks.model.Curso;
import br.com.thoughtworks.model.Topico;

public class TopicForm {

    private String titulo;
    private String mensagem;
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico parseTopic(CourseRepository repository) {
        return new Topico(titulo, mensagem, repository.findByNome(nomeCurso));
    }
}
