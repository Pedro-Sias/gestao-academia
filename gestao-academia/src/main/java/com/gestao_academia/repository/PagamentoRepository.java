package com.gestao_academia.repository;

import com.gestao_academia.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
}