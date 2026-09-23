package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Resposta;
import br.edu.ifg.labguide_professor.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professor/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private RespostaRepository respostaRepository;

    // Abre a tela HTML carregando os dados da resposta do banco
    @GetMapping("/{respostaId}")
    public String exibirTelaAvaliacao(@PathVariable Long respostaId, Model model) {
        Resposta resposta = respostaRepository.findById(respostaId)
                .orElseThrow(() -> new IllegalArgumentException("Resposta inválida:" + respostaId));

        model.addAttribute("resposta", resposta);
        return "avaliar-resposta"; // Procura o arquivo avaliar-resposta.html
    }

    // Recebe a nota digitada pelo professor no formulário HTML e salva no banco
    @PostMapping("/{respostaId}")
    public String salvarAvaliacao(@PathVariable Long respostaId,
                                  @RequestParam Double nota,
                                  @RequestParam String observacaoProfessor) {

        Resposta resposta = respostaRepository.findById(respostaId)
                .orElseThrow(() -> new IllegalArgumentException("Resposta inválida:" + respostaId));

        // Atualiza a resposta com os dados da avaliação
        resposta.setNota(nota);
        resposta.setObservacaoProfessor(observacaoProfessor);
        respostaRepository.save(resposta);

        return "redirect:/roteiros";
    }
}