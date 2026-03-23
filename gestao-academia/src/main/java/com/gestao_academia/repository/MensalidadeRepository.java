package com.gestao_academia.repository;

import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.model.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, UUID> {
    List<Mensalidade> findByAlunoId(UUID alunoId);

    List<Mensalidade> findByStatus(StatusPagamento status);
}