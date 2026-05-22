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
                                 @RequestParam("numero") Integer numero) {

        roteiroRepository.findById(id).ifPresent(roteiro -> {
            Passo novoPasso = new Passo();
            novoPasso.setDescricao(descricao);
            novoPasso.setNumero(numero);
            novoPasso.setRoteiro(roteiro); // Faz o vínculo da chave estrangeira (roteiro_id)

            passoRepository.save(novoPasso); // Salva no banco
        });

        // Redireciona de volta para a tela de visualização do mesmo roteiro
        return "redirect:/roteiros/visualizar/" + id;
    }
}