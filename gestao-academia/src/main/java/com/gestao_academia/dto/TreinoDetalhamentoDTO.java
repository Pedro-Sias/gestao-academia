package com.gestao_academia.dto;

import com.gestao_academia.model.Treino;
import com.gestao_academia.model.Serie; // Import importante!
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TreinoDetalhamentoDTO(
        UUID id,
        String nome,
        LocalDate dataCriacao,
        String nomeAluno,
        List<SerieResumoDTO> series
) {
    public TreinoDetalhamentoDTO(Treino t) {
        this(
                t.getId(),
                t.getNome(),
                t.getDataCriacao(),
                t.getAluno() != null ? t.getAluno().getNome() : "Sem Aluno",
                t.getSeries() != null ? t.getSeries().stream().map(SerieResumoDTO::new).toList() : List.of()
        );
    }
}


record SerieResumoDTO(String exercicio, int reps, float carga) {
    public SerieResumoDTO(Serie s) {
        this(
                s.getExercicio() != null ? s.getExercicio().getNome() : "Exercício não informado",
                s.getRepeticoes(),
                s.getCarga()
        );
    }
}