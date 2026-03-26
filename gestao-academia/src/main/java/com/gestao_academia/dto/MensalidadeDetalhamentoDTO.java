package com.gestao_academia.dto;
import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.model.StatusPagamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;




public record MensalidadeDetalhamentoDTO(
        UUID id,
        BigDecimal valor,
        LocalDate dataVencimento,
        StatusPagamento status,
        String nomeAluno
) {
    public MensalidadeDetalhamentoDTO(Mensalidade m) {
        this(
                m.getId(),
                m.getValor(),
                m.getDataVencimento(),
                m.getStatus(),
                m.getAluno() != null ? m.getAluno().getNome() : "Sem aluno"
        );
    }
}
