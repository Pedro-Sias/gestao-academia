package com.gestao_academia.dto;

import com.gestao_academia.model.Aluno;

import java.util.UUID;
public record AlunoDetalhamentoDTO(UUID id, String nome, String email, String matricula, String status) {
    public AlunoDetalhamentoDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getStatus());
    }
}
