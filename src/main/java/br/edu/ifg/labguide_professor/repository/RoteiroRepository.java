package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Roteiro;
import br.edu.ifg.labguide_professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoteiroRepository extends JpaRepository<Roteiro, Long> {
    // Busca todos os roteiros de um professor específico
    List<Roteiro> findByProfessor(Professor professor);
}