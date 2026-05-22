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

    // Mantemos o titulo
    @Column(nullable = false)
    private String titulo;

    // Adicionamos o nome de volta para satisfazer a constraint antiga do banco
    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    // Procure por essa linha na classe Roteiro.java:
    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passo> passos;

    // Construtor personalizado ajustado para duplicar o valor nas duas colunas
    public Roteiro(String titulo, String descricao, Turma turma, Professor professor) {
        this.titulo = titulo;
        this.nome = titulo; // O nome recebe o mesmo texto do titulo
        this.descricao = descricao;
        this.turma = turma;
        this.professor = professor;
    }
}