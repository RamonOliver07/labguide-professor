package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.repository.AlunoRepository;
import br.edu.ifg.labguide_professor.repository.RoteiroRepository;
import br.edu.ifg.labguide_professor.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping("/dashboard")
    public String carregarDashboard(Model model) {
        // Busca os totais reais no seu banco de dados
        long totalRoteiros = roteiroRepository.count();
        long totalAlunos = alunoRepository.count();
        long totalTurmas = turmaRepository.count();

        // Manda para o HTML
        model.addAttribute("totalRoteiros", totalRoteiros);
        model.addAttribute("totalAlunos", totalAlunos);
        model.addAttribute("totalTurmas", totalTurmas);

        return "dashboard"; // Vai procurar o dashboard.html
    }
}