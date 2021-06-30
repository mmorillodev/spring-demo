package br.com.thoughtworks.controller.DTO;

import br.com.thoughtworks.model.Resposta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public AnswerDTO(Resposta answer) {
        this.id = answer.getId();
        this.mensagem = answer.getMensagem();
        this.dataCriacao = answer.getDataCriacao();
        this.nomeAutor = answer.getAutor().getNome();
    }

    public static List<AnswerDTO> parseAnswer(List<Resposta> answers) {
        return answers.stream().map(AnswerDTO::new).collect(Collectors.toList());
    }
}
