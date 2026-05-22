package br.edu.ifg.labguide_professor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Diz que isso é uma tabela no banco
@Table(name = "tb_professor") // Define o nome da tabela
@Data // Cria Getters, Setters, toString, etc. (Lombok)
@NoArgsConstructor // Cria construtor vazio (obrigatório pelo JPA)
@AllArgsConstructor // Cria construtor com todos os campos
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;
}