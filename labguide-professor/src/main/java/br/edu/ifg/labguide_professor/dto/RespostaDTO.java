package br.edu.ifg.labguide_professor.dto;

import lombok.Data;

@Data
public class RespostaDTO {
    private Long alunoId;
    private Long passoId;
    private String conteudo; // Pode ser o texto que ele digitou, um número, ou o link da foto
}