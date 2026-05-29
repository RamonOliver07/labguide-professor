package br.edu.ifg.labguide_professor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita proteção CSRF só na API do celular (senão o celular não consegue mandar foto)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/mobile/**"))

                // 2. Regras de quem pode acessar o que
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/mobile/**").permitAll() // App do aluno passa direto sem login
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // Imagens e estilos liberados
                        .requestMatchers("/", "/index").permitAll() // Libera a página inicial pública!
                        .anyRequest().authenticated() // TODO O RESTO EXIGE LOGIN
                )

                // 3. Configurando a nossa tela bonitona
                .formLogin(form -> form
                        .loginPage("/login") // Endereço da nossa tela
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )

                // 4. Configurando o botão de Sair
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // <-- MUDOU AQUI: Agora volta para o Index (Vitrine) ao invés do Login!
                        .permitAll()
                );

        return http.build();
    }


}