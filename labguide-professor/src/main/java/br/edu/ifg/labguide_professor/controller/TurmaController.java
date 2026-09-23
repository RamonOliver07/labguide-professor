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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("professores", professorRepository.findAll());
        return "cadastro-turma";
    }

    @PostMapping("/salvar")
    public String salvar(Turma turma, @RequestParam("professorId") Long professorId) {
        // 1. Busca o professor
        professorRepository.findById(professorId).ifPresent(prof -> {
            turma.setProfessor(prof);
        });

        // 2. Salva a turma e guarda o objeto salvo (que agora tem um ID gerado pelo banco)
        Turma turmaSalva = turmaRepository.save(turma);

        // 3. Redireciona passando o parâmetro de sucesso E o ID da nova turma
        return "redirect:/turmas/novo?sucesso=true&turmaId=" + turmaSalva.getId();
    }
}