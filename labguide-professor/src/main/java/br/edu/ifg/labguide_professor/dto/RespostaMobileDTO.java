package br.edu.ifg.labguide_professor.dto;

public class RespostaMobileDTO {

    // Novo campo para identificar quem está respondendo
    private String matricula;

    // Usamos Object para aceitar String, Boolean ou Integer do mobile
    private Object resposta;

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Object getResposta() { return resposta; }
    public void setResposta(Object resposta) { this.resposta = resposta; }
}