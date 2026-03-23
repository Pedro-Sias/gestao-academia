package com.gestao_academia.repository;

import com.gestao_academia.model.HistoricoCarga;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoricoCargaRepository extends JpaRepository<HistoricoCarga, UUID> {
    List<HistoricoCarga> findByAlunoId(UUID alunoId);

    Optional<HistoricoCarga> findFirstByAlunoIdAndExercicioIdOrderByDataRegistroDesc(UUID alunoId, UUID exercicioId);
}