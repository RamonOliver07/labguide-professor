package br.edu.ifg.labguide_professor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String telaLogin() {
        return "login"; // Vai procurar o arquivo login.html
    }
}