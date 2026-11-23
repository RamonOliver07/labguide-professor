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
                .orElseThrow(() -> new IllegalArgumentException("Resposta inválida: " + respostaId));

        model.addAttribute("resposta", resposta);
        return "avaliar-resposta";
    }

    // Salva a nota e redireciona de volta para a matriz de respostas do roteiro
    @PostMapping("/{id}")
    public String salvarAvaliacao(@PathVariable Long id,
                                  @RequestParam("nota") Double nota,
                                  @RequestParam(value = "observacaoProfessor", required = false) String observacaoProfessor) {

        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resposta não encontrada: " + id));

        resposta.setNota(nota);
        resposta.setObservacaoProfessor(observacaoProfessor);
        respostaRepository.save(resposta);

        Long roteiroId = resposta.getPasso().getRoteiro().getId();
        return "redirect:/roteiros/visualizar/" + roteiroId + "/respostas";
    }
}