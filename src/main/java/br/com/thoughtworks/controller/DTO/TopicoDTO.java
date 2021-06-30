package br.com.thoughtworks.controller.DTO;

import br.com.thoughtworks.model.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDTO(Topico topic) {
        this.id = topic.getId();
        this.titulo = topic.getTitulo();
        this.mensagem = topic.getMensagem();
        this.dataCriacao = topic.getDataCriacao();
    }

    public static TopicoDTO parseTopic(Topico topic) {
        return new TopicoDTO(topic);
    }

    public static Page<TopicoDTO> parseTopic(Page<Topico> topics) {
        return topics.map(TopicoDTO::new);
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
