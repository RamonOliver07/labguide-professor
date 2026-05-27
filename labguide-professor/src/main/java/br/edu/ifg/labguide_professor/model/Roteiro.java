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

    // NOVO CAMPO: Controla se o roteiro está visível para os alunos
    @Column(name = "status", length = 50, nullable = false)
    private String status = "RASCUNHO"; // Valor inicial padrão

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passo> passos;

    public Roteiro(String titulo, String descricao, Turma turma, Professor professor) {
        this.titulo = titulo;
        this.nome = titulo;
        this.descricao = descricao;
        this.turma = turma;
        this.professor = professor;
        this.status = "RASCUNHO"; // Garante o padrão no construtor
    }


}