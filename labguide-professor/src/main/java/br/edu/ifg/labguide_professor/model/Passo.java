package br.edu.ifg.labguide_professor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_passo")
public class Passo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description; // Caso no seu banco esteja como descricao, mude para descricao

    @ManyToOne
    @JoinColumn(name = "roteiro_id", nullable = false)
    private Roteiro roteiro;

    // --- GETTERS E SETTERS MANUAIS (Garante que o Controller e o Thymeleaf vão enxergar) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getDescricao() { return description; }
    public void setDescricao(String descricao) { this.description = descricao; }

    public Roteiro getRoteiro() { return roteiro; }
    public void setRoteiro(Roteiro roteiro) { this.roteiro = roteiro; }
}