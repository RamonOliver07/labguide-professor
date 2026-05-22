package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Professor;
import br.edu.ifg.labguide_professor.model.Turma;
import br.edu.ifg.labguide_professor.repository.ProfessorRepository;
import br.edu.ifg.labguide_professor.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/novo")
    public String formulario(Model model) {
        // Passamos a lista de professores para o HTML escolher quem é o dono da turma
        model.addAttribute("professores", professorRepository.findAll());
        return "cadastro-turma";
    }

    @PostMapping("/salvar")
    public String salvar(Turma turma) {
        turmaRepository.save(turma);
        return "redirect:/professores"; // Redireciona para onde você achar melhor
    }
}