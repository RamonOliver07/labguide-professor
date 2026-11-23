package br.edu.ifg.labguide_professor.controller;

import br.edu.ifg.labguide_professor.model.Professor;
import br.edu.ifg.labguide_professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/login")
    public String telaLogin() {
        return "login"; // Vai procurar o arquivo login.html
    }

    @PostMapping("/professores/cadastrar")
    public String cadastrarProfessor(@RequestParam("nome") String nome,
                                     @RequestParam("email") String email,
                                     @RequestParam("senha") String senha) {

        // Evita erro de duplicidade (pois o e-mail é UNIQUE no banco)
        if (professorRepository.findByEmail(email).isPresent()) {
            return "redirect:/login?emailExiste";
        }

        Professor novoProfessor = new Professor();
        novoProfessor.setNome(nome);
        novoProfessor.setEmail(email);
        novoProfessor.setSenha(senha); // Mantendo o padrão em texto simples do banco

        professorRepository.save(novoProfessor);

        return "redirect:/login?cadastrado";
    }
}