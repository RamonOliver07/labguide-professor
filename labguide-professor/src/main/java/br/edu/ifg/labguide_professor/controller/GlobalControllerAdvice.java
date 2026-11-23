package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Professor;
import br.edu.ifg.labguide_professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ProfessorRepository professorRepository;

    @ModelAttribute("nomeProfessorLogado")
    public String adicionarNomeProfessor(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return professorRepository.findByEmail(email)
                    .map(Professor::getNome) // Retorna "Ramon Oliveira"
                    .orElse(email);
        }
        return "Professor";
    }
}