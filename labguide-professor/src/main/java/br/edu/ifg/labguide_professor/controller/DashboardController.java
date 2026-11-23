package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Aluno;
import br.edu.ifg.labguide_professor.model.Resposta;
import br.edu.ifg.labguide_professor.model.Roteiro;
import br.edu.ifg.labguide_professor.model.Turma;
import br.edu.ifg.labguide_professor.repository.AlunoRepository;
import br.edu.ifg.labguide_professor.repository.RespostaRepository;
import br.edu.ifg.labguide_professor.repository.RoteiroRepository;
import br.edu.ifg.labguide_professor.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @GetMapping("/dashboard")
    public String carregarDashboard(Model model) {
        // Busca as listas completas no banco de dados
        List<Roteiro> listaRoteiros = roteiroRepository.findAll();
        List<Turma> listaTurmas = turmaRepository.findAll();
        List<Aluno> listaAlunos = alunoRepository.findAll();
        List<Resposta> listaRespostas = respostaRepository.findAll();

        // Envia os totais para os cards superiores
        model.addAttribute("totalRoteiros", listaRoteiros.size());
        model.addAttribute("totalTurmas", listaTurmas.size());
        model.addAttribute("totalAlunos", listaAlunos.size());

        // Envia as listas para o painel lateral interativo (Offcanvas)
        model.addAttribute("listaRoteiros", listaRoteiros);
        model.addAttribute("listaTurmas", listaTurmas);
        model.addAttribute("listaAlunos", listaAlunos);
        model.addAttribute("listaRespostas", listaRespostas);

        return "dashboard"; // Vai procurar o dashboard.html
    }
}