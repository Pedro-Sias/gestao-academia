package com.gestao_academia.repository;

import com.gestao_academia.model.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, UUID> {
    // Esse aqui é um bônus: pra você achar as contas de um aluno específico depois
    List<Mensalidade> findByAlunoId(UUID alunoId);
}