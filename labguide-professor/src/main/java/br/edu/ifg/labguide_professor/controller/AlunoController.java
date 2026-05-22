package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Aluno;
import br.edu.ifg.labguide_professor.model.Turma;
import br.edu.ifg.labguide_professor.repository.AlunoRepository;
import br.edu.ifg.labguide_professor.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository; // ADICIONE ESTA LINHA

    @GetMapping
    public String listar(Model model) {
        List<Aluno> listaAlunos = alunoRepository.findAll();
        model.addAttribute("lista", listaAlunos);
        return "lista-alunos";
    }

    @GetMapping("/novo")
    public String formulario(Model model) {

        // Busca todas as turmas cadastradas para o aluno poder escolher uma
        model.addAttribute("turmas", turmaRepository.findAll());
        return "cadastro-aluno";


    }

    @PostMapping("/salvar")
// Veja que o @RequestParam agora procura exatamente por "turmaId"
    public String salvar(Aluno aluno, @RequestParam("turmaId") Long turmaId) {

        // Busca a turma no banco de dados usando o ID
        Turma turmaEscolhida = turmaRepository.findById(turmaId).orElse(null);

        // Coloca a turma dentro do aluno
        aluno.setTurma(turmaEscolhida);

        // Salva o aluno no banco
        alunoRepository.save(aluno);

        return "redirect:/alunos";
    }
}