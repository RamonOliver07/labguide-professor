package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Roteiro;
import br.edu.ifg.labguide_professor.model.Passo;
import br.edu.ifg.labguide_professor.repository.RoteiroRepository;
import br.edu.ifg.labguide_professor.repository.TurmaRepository;
import br.edu.ifg.labguide_professor.repository.ProfessorRepository;
import br.edu.ifg.labguide_professor.repository.PassoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/roteiros")
public class RoteiroController {

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private PassoRepository passoRepository;

    // Tela de cadastro de novo roteiro
    @GetMapping("/novo")
    public String novoRoteiro(Model model) {
        model.addAttribute("roteiro", new Roteiro());
        model.addAttribute("turmas", turmaRepository.findAll()); // Carrega as turmas para o <select>
        return "cadastro-roteiro";
    }

    // Salvar o roteiro no banco associando Turma e Professor
    @PostMapping("/salvar")
    public String salvarRoteiro(@ModelAttribute("roteiro") Roteiro roteiro,
                                @RequestParam("turmaId") Long turmaId,
                                @RequestParam("titulo") String titulo) {

        // Força o preenchimento dos dois campos com o que veio da tela
        roteiro.setTitulo(titulo);
        roteiro.setNome(titulo);
        roteiro.setStatus("RASCUNHO");

        // Vincula a turma selecionada na tela ao roteiro
        turmaRepository.findById(turmaId).ifPresent(roteiro::setTurma);

        // Busca o primeiro professor para testes provisórios
        professorRepository.findAll().stream().findFirst().ifPresent(roteiro::setProfessor);

        // Salva no banco de dados
        roteiroRepository.save(roteiro);

        return "redirect:/roteiros/novo?sucesso";

    }

    // Tela que lista todos os roteiros cadastrados
    @GetMapping
    public String listarRoteiros(Model model) {
        // Busca todos os roteiros salvos no banco de dados
        model.addAttribute("roteiros", roteiroRepository.findAll());
        return "lista-roteiros";
    }

    // Rota para Visualizar o Roteiro e gerenciar os Passos
    @GetMapping("/visualizar/{id}")
    public String visualizarRoteiro(@PathVariable("id") Long id, Model model) {
        roteiroRepository.findById(id).ifPresent(roteiro -> model.addAttribute("roteiro", roteiro));
        return "visualizar-roteiro";
    }

    // Rota para abrir a tela de Edicao carregando os dados antigos
    @GetMapping("/editar/{id}")
    public String editarRoteiro(@PathVariable("id") Long id, Model model) {
        roteiroRepository.findById(id).ifPresent(roteiro -> {
            model.addAttribute("roteiro", roteiro);
            model.addAttribute("turmas", turmaRepository.findAll()); // Carrega as turmas caso ele queira mudar
        });
        return "editar-roteiro";
    }

    // Rota para receber os dados da tela e salvar um novo passo vinculado a este roteiro
    @PostMapping("/visualizar/{id}/adicionar-passo")
    public String adicionarPasso(@PathVariable("id") Long id,
                                 @RequestParam("descricao") String descricao,
                                 @RequestParam("numero") Integer numero,
                                 @RequestParam("tipoEntrada") String tipoEntrada) { // <--- CAPTURA O TIPO DE ENTRADA DO FORMULÁRIO

        roteiroRepository.findById(id).ifPresent(roteiro -> {
            Passo novoPasso = new Passo();
            novoPasso.setDescricao(descricao);
            novoPasso.setNumero(numero);
            novoPasso.setTipoEntrada(tipoEntrada); // <--- SALVA O TIPO NO OBJETO PASSO
            novoPasso.setRoteiro(roteiro);

            passoRepository.save(novoPasso);
        });

        return "redirect:/roteiros/visualizar/" + id;
    }

    // Rota para EXCLUIR um passo específico e atualizar a memória do Java
    @PostMapping("/visualizar/{roteiroId}/excluir-passo/{passoId}")
    public String excluirPasso(@PathVariable("roteiroId") Long roteiroId,
                               @PathVariable("passoId") Long passoId) {

        roteiroRepository.findById(roteiroId).ifPresent(roteiro -> {
            roteiro.getPassos().removeIf(passo -> passo.getId().equals(passoId));

            for (int i = 0; i < roteiro.getPassos().size(); i++) {
                roteiro.getPassos().get(i).setNumero(i + 1);
            }

            roteiroRepository.save(roteiro);
        });

        passoRepository.deleteById(passoId);

        return "redirect:/roteiros/visualizar/" + roteiroId;
    }

    // Rota para EDITAR um passo existente
    @PostMapping("/visualizar/{roteiroId}/editar-passo/{passoId}")
    public String editarPasso(@PathVariable("roteiroId") Long roteiroId,
                              @PathVariable("passoId") Long passoId,
                              @RequestParam("descricao") String descricao,
                              @RequestParam("numero") Integer numero,
                              @RequestParam("tipoEntrada") String tipoEntrada) { // <--- PERMITE ALTERAR O TIPO NA EDIÇÃO

        passoRepository.findById(passoId).ifPresent(passo -> {
            passo.setDescricao(descricao);
            passo.setNumero(numero);
            passo.setTipoEntrada(tipoEntrada); // <--- ATUALIZA O TIPO NO BANCO
            passoRepository.save(passo);
        });

        return "redirect:/roteiros/visualizar/" + roteiroId;
    }

    // Rota para ALTERAR O STATUS do roteiro para PUBLICADO
    @PostMapping("/visualizar/{id}/publicar")
    public String publicarRoteiro(@PathVariable("id") Long id) {

        roteiroRepository.findById(id).ifPresent(roteiro -> {
            roteiro.setStatus("PUBLICADO"); // <--- ALTERA O STATUS NO BANCO
            roteiroRepository.save(roteiro);
        });

        return "redirect:/roteiros/visualizar/" + id;

    }
    // Rota para DUPLICAR (CLONAR) um roteiro inteiro com seus passos
    @PostMapping("/visualizar/{id}/duplicar")
    public String duplicarRoteiro(@PathVariable("id") Long id) {

        Optional<Roteiro> roteiroOriginalOpt = roteiroRepository.findById(id);

        if (roteiroOriginalOpt.isPresent()) {
            Roteiro original = roteiroOriginalOpt.get();

            // 1. Cria a cópia do Cabeçalho do Roteiro
            Roteiro copia = new Roteiro();
            copia.setTitulo(original.getTitulo() + " (Cópia)");
            copia.setNome(original.getNome() + " (Cópia)");
            copia.setDescricao(original.getDescricao());
            copia.setTurma(original.getTurma()); // Mantém a turma atual, mas o professor pode editar depois
            copia.setProfessor(original.getProfessor());
            copia.setStatus("RASCUNHO"); // A cópia sempre nasce como rascunho por segurança

            // Salva o roteiro novo para gerar o ID dele no banco
            Roteiro roteiroSalvo = roteiroRepository.save(copia);

            // 2. Copia todos os Passos (se existirem)
            if (original.getPassos() != null && !original.getPassos().isEmpty()) {
                for (Passo passoOriginal : original.getPassos()) {
                    Passo passoCopia = new Passo();
                    passoCopia.setNumero(passoOriginal.getNumero());
                    passoCopia.setDescricao(passoOriginal.getDescricao());
                    passoCopia.setTipoEntrada(passoOriginal.getTipoEntrada()); // Texto, Numero ou Foto
                    passoCopia.setRoteiro(roteiroSalvo); // Vincula o passo clonado ao ROTEIRO NOVO

                    passoRepository.save(passoCopia);
                }
            }

            // Redireciona o professor direto para a TELA DE EDIÇÃO do roteiro recém-clonado
            return "redirect:/roteiros/editar/" + roteiroSalvo.getId();
        }

        return "redirect:/roteiros";
    }

    // Rota para EXCLUIR o roteiro inteiro
    @PostMapping("/visualizar/{id}/excluir")
    public String excluirRoteiro(@PathVariable("id") Long id) {
        roteiroRepository.deleteById(id);
        // Volta para a lista principal após apagar
        return "redirect:/roteiros";
    }
}