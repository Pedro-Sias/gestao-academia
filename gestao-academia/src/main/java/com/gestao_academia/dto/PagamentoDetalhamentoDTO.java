package com.gestao_academia.dto;

import com.gestao_academia.model.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoDetalhamentoDTO(UUID id, LocalDateTime dataPagamento, String formaPagamento) {
    public PagamentoDetalhamentoDTO(Pagamento p) {
        this(p.getId(), p.getDataPagamento(), p.getFormaPagamento());
    }
}