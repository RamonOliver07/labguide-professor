package br.edu.ifg.labguide_professor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_resposta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String conteudo; // Aqui fica o texto ou link da foto do aluno

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio = LocalDateTime.now();

    // Relacionamento: Uma resposta pertence a um Aluno
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    // Relacionamento: Uma resposta refere-se a um Passo específico do roteiro
    @ManyToOne
    @JoinColumn(name = "passo_id", nullable = false)
    private Passo passo;
}