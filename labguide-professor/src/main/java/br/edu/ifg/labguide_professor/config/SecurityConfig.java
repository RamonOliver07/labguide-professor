package br.edu.ifg.labguide_professor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Injeta o filtro JWT que criamos no passo anterior
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita proteção CSRF na API do celular e na rota de login mobile
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/mobile/**", "/auth/login"))

                // 2. Regras de quem pode acessar o que
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/mobile/**").permitAll() // App do aluno passa sem erro 302
                        .requestMatchers("/auth/login").permitAll() // Libera a rota de login do mobile
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // Imagens e estilos liberados
                        .requestMatchers("/", "/index").permitAll() // Libera a página inicial pública!
                        .anyRequest().authenticated() // TODO O RESTO EXIGE LOGIN
                )

                // 3. Configurando a nossa tela bonitona para o Professor (Usa sessão web normal)
                .formLogin(form -> form
                        .loginPage("/login") // Endereço da nossa tela
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )

                // 4. Configurando o botão de Sair do Professor
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // Volta para o Index (Vitrine) ao invés do Login!
                        .permitAll()
                )

                // 5. Adiciona o filtro JWT para interceptar o token do celular antes da segurança padrão
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}