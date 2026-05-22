package br.edu.ifg.labguide_professor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.edu.ifg.labguide_professor.model")
@EnableJpaRepositories(basePackages = "br.edu.ifg.labguide_professor.repository")
public class LabguideProfessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabguideProfessorApplication.class, args);
    }
}