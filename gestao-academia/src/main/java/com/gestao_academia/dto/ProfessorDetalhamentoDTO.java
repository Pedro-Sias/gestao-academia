package com.gestao_academia.dto;

import com.gestao_academia.model.Professor;

import java.util.UUID;

public record ProfessorDetalhamentoDTO(UUID id, String nome, String especialidade) {
    public ProfessorDetalhamentoDTO(Professor professor) {
        this(professor.getId(), professor.getNome(), professor.getEspecialidade());
    }
}
