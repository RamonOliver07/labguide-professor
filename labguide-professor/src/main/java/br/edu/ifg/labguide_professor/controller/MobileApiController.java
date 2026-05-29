package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.dto.RespostaDTO;
import br.edu.ifg.labguide_professor.model.Aluno;
import br.edu.ifg.labguide_professor.model.Passo;
import br.edu.ifg.labguide_professor.model.Resposta;
import br.edu.ifg.labguide_professor.repository.AlunoRepository;
import br.edu.ifg.labguide_professor.repository.PassoRepository;
import br.edu.ifg.labguide_professor.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = "*") // Permite que qualquer aplicativo mobile converse com essa API
public class MobileApiController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PassoRepository passoRepository;

    // Rota que o celular vai chamar para enviar as respostas
    @PostMapping("/enviar-resposta")
    public ResponseEntity<String> receberResposta(@RequestBody RespostaDTO dto) {

        Optional<Aluno> alunoOpt = alunoRepository.findById(dto.getAlunoId());
        Optional<Passo> passoOpt = passoRepository.findById(dto.getPassoId());

        if (alunoOpt.isPresent() && passoOpt.isPresent()) {
            Resposta novaResposta = new Resposta();
            novaResposta.setAluno(alunoOpt.get());
            novaResposta.setPasso(passoOpt.get());
            novaResposta.setConteudo(dto.getConteudo());
            novaResposta.setDataEnvio(LocalDateTime.now()); // Grava a hora exata

            respostaRepository.save(novaResposta);

            // Retorna um sinal de "OK" (Status 200) para o celular
            return ResponseEntity.ok("Resposta enviada com sucesso para o professor!");
        }

        // Retorna um erro (Status 400) se mandar ID errado
        return ResponseEntity.badRequest().body("Erro: Aluno ou Passo não existem no banco de dados.");
    }
}