package br.edu.ifg.labguide_professor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_passo")
public class Passo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Garante o vínculo com a coluna 'ordem'
    @Column(name = "ordem", nullable = false)
    private Integer numero;

    // Garante o vínculo com a coluna 'instrucao'
    @Column(name = "instrucao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "roteiro_id", nullable = false)
    private Roteiro roteiro;

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Roteiro getRoteiro() { return roteiro; }
    public void setRoteiro(Roteiro roteiro) { this.roteiro = roteiro; }
}