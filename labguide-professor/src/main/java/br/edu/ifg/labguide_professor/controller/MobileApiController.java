package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.dto.PassoMobileDTO;
import br.edu.ifg.labguide_professor.dto.RespostaMobileDTO;
import br.edu.ifg.labguide_professor.model.Aluno;
import br.edu.ifg.labguide_professor.model.Passo;
import br.edu.ifg.labguide_professor.model.Resposta;
import br.edu.ifg.labguide_professor.repository.AlunoRepository;
import br.edu.ifg.labguide_professor.repository.PassoRepository;
import br.edu.ifg.labguide_professor.repository.RespostaRepository;
import br.edu.ifg.labguide_professor.repository.RoteiroRepository;
import br.edu.ifg.labguide_professor.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = "*")
public class MobileApiController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PassoRepository passoRepository;

    // Adicionados para resolver o erro:
    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    // Rota 1: Mobile envia os dados de um passo concluído (texto e imagem)
    @PostMapping("/experimentos/{experimentoId}/passo/{passoId}")
    public ResponseEntity<String> registrarPasso(
            @PathVariable Long experimentoId,
            @PathVariable Long passoId,
            @RequestBody PassoMobileDTO payload) {

        // TODO: Substituir 1L pelo ID do aluno extraído do Token JWT futuramente
        Long alunoIdDoJwt = 1L;

        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoIdDoJwt);
        Optional<Passo> passoOpt = passoRepository.findById(passoId);

        if (alunoOpt.isPresent() && passoOpt.isPresent()) {
            Resposta novaResposta = new Resposta();
            novaResposta.setAluno(alunoOpt.get());
            novaResposta.setPasso(passoOpt.get());

            // Junta o texto e a imagem do DTO para salvar no conteúdo
            String conteudoFinal = "Texto: " + payload.getTexto() + " | Imagem: " + payload.getImagem();
            novaResposta.setConteudo(conteudoFinal);
            novaResposta.setDataEnvio(LocalDateTime.now());

            respostaRepository.save(novaResposta);
            return ResponseEntity.ok("Evidências do passo registradas com sucesso!");
        }

        return ResponseEntity.badRequest().body("Erro: Aluno ou Passo não encontrados.");
    }

    // Rota 3: Mobile busca a lista de experimentos (roteiros) filtrada por turma
    @GetMapping("/experimentos")
    public ResponseEntity<?> listarExperimentosMobile(@RequestParam(value = "turmaId", required = false) Long turmaId) {

        // Se o celular enviar o ID da turma na URL, nós filtramos
        if (turmaId != null) {
            var roteirosDaTurma = roteiroRepository.findAll().stream()
                    .filter(roteiro -> roteiro.getTurma() != null && roteiro.getTurma().getId().equals(turmaId))
                    .toList();
            return ResponseEntity.ok(roteirosDaTurma);
        }

        // Se o celular não enviar nada, devolve a lista inteira por segurança
        return ResponseEntity.ok(roteiroRepository.findAll());
    }

    // Rota 4: Mobile busca a lista de turmas (Formatada para o App)
    @GetMapping("/turma")
    public ResponseEntity<?> listarTurmasMobile() {

        var turmasDoBanco = turmaRepository.findAll();

        var turmasFormatadas = turmasDoBanco.stream().map(turma -> {
            java.util.Map<String, Object> turmaMobile = new java.util.HashMap<>();
            turmaMobile.put("id", turma.getId());
            turmaMobile.put("nomeTurma", turma.getNome());

            // Como a sua Turma não tem código, enviamos vazio para satisfazer o aplicativo
            turmaMobile.put("codigoTurma", "");

            if (turma.getProfessor() != null) {
                turmaMobile.put("nomeProfessor", turma.getProfessor().getNome());
            } else {
                turmaMobile.put("nomeProfessor", "Sem Professor");
            }

            return turmaMobile;
        }).toList();

        return ResponseEntity.ok(turmasFormatadas);
    }

    // Rota 2: Mobile envia respostas diretas (questionários)
    @PostMapping("/experimentos/{experimentoId}/passo/{passoId}/respostas")
    public ResponseEntity<String> registrarResposta(
            @PathVariable Long experimentoId,
            @PathVariable Long passoId,
            @RequestBody RespostaMobileDTO payload) {

        // TODO: Substituir 1L pelo ID do aluno extraído do Token JWT futuramente
        Long alunoIdDoJwt = 1L;

        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoIdDoJwt);
        Optional<Passo> passoOpt = passoRepository.findById(passoId);

        if (alunoOpt.isPresent() && passoOpt.isPresent()) {
            Resposta novaResposta = new Resposta();
            novaResposta.setAluno(alunoOpt.get());
            novaResposta.setPasso(passoOpt.get());

            // Converte qualquer dado (booleano, número) para String antes de salvar
            novaResposta.setConteudo(String.valueOf(payload.getResposta()));
            novaResposta.setDataEnvio(LocalDateTime.now());

            respostaRepository.save(novaResposta);
            return ResponseEntity.ok("Resposta enviada com sucesso para o professor!");
        }

        return ResponseEntity.badRequest().body("Erro: Aluno ou Passo não encontrados.");
    }
}