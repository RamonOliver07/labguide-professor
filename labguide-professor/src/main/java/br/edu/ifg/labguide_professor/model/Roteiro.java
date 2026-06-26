package br.edu.ifg.labguide_professor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tb_roteiro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "status", length = 50, nullable = false)
    private String status = "RASCUNHO";

    @Column(columnDefinition = "TEXT")
    private String problematizacao;

    @Column(columnDefinition = "TEXT")
    private String materiais;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passo> passos;

    // CONSTRUTOR ATUALIZADO COM OS NOVOS CAMPOS
    public Roteiro(String titulo, String descricao, String problematizacao, String materiais, Turma turma, Professor professor) {
        this.titulo = titulo;
        this.nome = titulo;
        this.descricao = descricao;
        this.problematizacao = problematizacao;
        this.materiais = materiais;
        this.turma = turma;
        this.professor = professor;
        this.status = "RASCUNHO";
    }
}