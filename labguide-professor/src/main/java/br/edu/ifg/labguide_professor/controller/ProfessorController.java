
package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Professor;
import br.edu.ifg.labguide_professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Mude de @RestController para @Controller para usar HTML
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    // Abre a página de cadastro
    @GetMapping("/novo")
    public String formulario() {
        return "cadastro-professor";
    }

    // Recebe os dados do formulário e salva
    @PostMapping("/salvar")
    public String salvar(Professor professor) {
        repository.save(professor);
        return "redirect:/professores"; // Após salvar, volta para a lista
    }

    @GetMapping
    public String listar(org.springframework.ui.Model model) {
        List<Professor> professores = repository.findAll();
        model.addAttribute("lista", professores); // Passa a lista do banco para o HTML
        return "lista-professores"; // Nome do arquivo HTML que criamos
    }
}