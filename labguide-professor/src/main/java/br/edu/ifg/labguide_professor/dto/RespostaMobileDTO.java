package br.edu.ifg.labguide_professor.dto;

public class RespostaMobileDTO {
    // Usamos Object para aceitar String, Boolean ou Integer do mobile sem dar erro
    private Object resposta;

    public Object getResposta() { return resposta; }
    public void setResposta(Object resposta) { this.resposta = resposta; }
}