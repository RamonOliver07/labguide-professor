package br.edu.ifg.labguide_professor.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Key;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Uma chave secreta qualquer de pelo menos 32 caracteres para assinar o token do seu TCC
    private final String CHAVE_SECRETA = "minha-chave-secreta-muito-longa-do-tcc-ifg-123456";
    private final Key key = Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes());

    @PostMapping("/login")
    public ResponseEntity<RespostaToken> loginMobile(@RequestBody RequisicaoLogin login) {

        // Aqui depois você pode colocar um 'if' para checar a senha no banco de dados.
        // Por enquanto, para o teste rodar, vamos gerar o token direto.

        String tokenGerado = Jwts.builder()
                .setSubject(login.getMatricula()) // Colocamos a matrícula do aluno dentro do token
                .setIssuedAt(new Date()) // Data de agora
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token vale por 1 dia (em milissegundos)
                .signWith(key)
                .compact();

        // Devolve o token no formato JSON que o celular espera
        return ResponseEntity.ok(new RespostaToken(tokenGerado));
    }

    // --- Classes DTO auxiliares (colocadas aqui para não precisar criar arquivos separados) ---

    public static class RequisicaoLogin {
        private String matricula;
        private String senha;

        public String getMatricula() { return matricula; }
        public void setMatricula(String matricula) { this.matricula = matricula; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class RespostaToken {
        private String token;

        public RespostaToken(String token) { this.token = token; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}