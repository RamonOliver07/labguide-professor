package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Turma;
import br.edu.ifg.labguide_professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Busca todas as turmas de um professor (útil para o Dashboard)
    List<Turma> findByProfessor(Professor professor);
}