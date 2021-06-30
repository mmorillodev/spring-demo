package br.com.thoughtworks.controller.form;

import br.com.thoughtworks.model.Topico;
import br.com.thoughtworks.repository.TopicRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicUpdateForm {

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Size(min = 10)
    private String mensagem;

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

    public Topico update(Long id, TopicRepository topicRepository) {
        Topico topic = topicRepository.getById(id);
        topic.setTitulo(titulo);
        topic.setMensagem(mensagem);

        return topic;
    }
}
