package br.edu.ifg.labguide_professor.config;

import br.edu.ifg.labguide_professor.model.Professor;
import br.edu.ifg.labguide_professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfessorDetailsService implements UserDetailsService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Vai à base de dados procurar o professor pelo e-mail usando o seu Optional
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado com o email: " + email));

        // Se encontrou, entrega os dados ao Spring Security
        return User.builder()
                .username(professor.getEmail())
                // O {noop} diz ao Spring que as senhas não estão criptografadas no banco (facilita o TCC)
                .password("{noop}" + professor.getSenha())
                .roles("PROFESSOR")
                .build();
    }
}