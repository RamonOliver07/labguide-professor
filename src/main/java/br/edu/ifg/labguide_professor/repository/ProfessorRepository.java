package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Esta linha permite buscar um professor pelo e-mail no login futuramente
    Optional<Professor> findByEmail(String email);
}