package br.edu.ifg.labguide_professor.repository;

import br.edu.ifg.labguide_professor.model.Passo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassoRepository extends JpaRepository<Passo, Long> {
}