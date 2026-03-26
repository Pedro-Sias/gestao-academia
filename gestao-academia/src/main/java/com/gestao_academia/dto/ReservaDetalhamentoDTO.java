package com.gestao_academia.dto;
import com.gestao_academia.model.Reserva;
import java.time.LocalDateTime;
import java.util.UUID;



public record ReservaDetalhamentoDTO(
        UUID id,
        String status,
        String nomeAluno,
        String nomeAula,
        LocalDateTime dataAula
) {
    public ReservaDetalhamentoDTO(Reserva r) {
        this(
                r.getId(),
                r.getStatus(),
                r.getAluno().getNome(),
                r.getAula().getNome(),
                r.getAula().getDataHora()
        );
    }
}
