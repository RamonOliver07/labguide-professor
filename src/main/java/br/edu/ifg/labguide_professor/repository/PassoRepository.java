package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Passo;
import br.edu.ifg.labguide_professor.model.Roteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PassoRepository extends JpaRepository<Passo, Long> {
    // Busca os passos de um roteiro ordenados (fundamental para a experiência do aluno)
    List<Passo> findByRoteiroOrderByOrdemAsc(Roteiro roteiro);
}