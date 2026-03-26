package com.gestao_academia.dto;

import com.gestao_academia.model.Professor;
import java.util.UUID;

public record ProfessorDetalhamentoDTO(UUID id, String nome, String especialidade, String registroProfissional) {
    public ProfessorDetalhamentoDTO(Professor p) {
        this(p.getId(), p.getNome(), p.getEspecialidade(), p.getRegistroProfissional());
    }
}