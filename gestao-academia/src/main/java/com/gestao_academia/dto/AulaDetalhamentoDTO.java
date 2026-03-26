package com.gestao_academia.dto;
import com.gestao_academia.model.Aula;
import java.time.LocalDateTime;
import java.util.UUID;



public record AulaDetalhamentoDTO(UUID id, String nome, LocalDateTime dataHora, int vagas, String nomeProfessor) {
    public AulaDetalhamentoDTO(Aula a){
        this(
                a.getId(),
                a.getNome(),
                a.getDataHora(),
                a.getVagas(),
                a.getProfessor() != null ?a.getProfessor().getNome() : "Sem professor"
        );
    }
}
