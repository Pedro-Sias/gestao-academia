package com.gestao_academia.dto;

import com.gestao_academia.model.Exercicio;

import java.util.UUID;

public record ExercicioDetalhamentoDTO(UUID id, String nome, String descricao, String videoUrl) {
    public ExercicioDetalhamentoDTO(Exercicio exercicio) {
        this(exercicio.getId(), exercicio.getNome(), exercicio.getDescricao(), exercicio.getVideoUrl());
    }
}
