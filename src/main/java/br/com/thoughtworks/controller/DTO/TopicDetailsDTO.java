package br.com.thoughtworks.controller.DTO;

import br.com.thoughtworks.model.StatusTopico;
import br.com.thoughtworks.model.Topico;

import java.time.LocalDateTime;
import java.util.List;

public class TopicDetailsDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<AnswerDTO> answers;

    public TopicDetailsDTO(Topico topic) {
        this.id = topic.getId();
        this.titulo = topic.getTitulo();
        this.mensagem = topic.getMensagem();
        this.dataCriacao = topic.getDataCriacao();
        this.nomeAutor = topic.getAutor().getNome();
        this.status = topic.getStatus();
        this.answers = AnswerDTO.parseAnswer(topic.getRespostas());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
