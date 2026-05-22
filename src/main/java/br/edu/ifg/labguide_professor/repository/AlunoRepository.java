package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Aluno;
import br.edu.ifg.labguide_professor.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Busca aluno pela matrícula (essencial para o login no App)
    Optional<Aluno> findByMatricula(String matricula);

    // Lista todos os alunos de uma turma específica
    List<Aluno> findByTurma(Turma turma);
}