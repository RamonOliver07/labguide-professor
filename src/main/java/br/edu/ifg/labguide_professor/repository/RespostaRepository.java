package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Resposta;
import br.edu.ifg.labguide_professor.model.Aluno;
import br.edu.ifg.labguide_professor.model.Passo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    // Busca todas as respostas de um aluno específico
    List<Resposta> findByAluno(Aluno aluno);

    // Busca todas as respostas dadas a um passo específico de um roteiro
    List<Resposta> findByPasso(Passo passo);
}