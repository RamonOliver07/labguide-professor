package br.edu.ifg.labguide_professor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_passo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer ordem; // Ex: 1, 2, 3...

    @Column(columnDefinition = "TEXT", nullable = false)
    private String instrucao;

    @Column(name = "tipo_entrada")
    private String tipoEntrada; // Ex: "texto", "foto", "numero"

    @ManyToOne
    @JoinColumn(name = "roteiro_id", nullable = false)
    private Roteiro roteiro;
}