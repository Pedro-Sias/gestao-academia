package com.gestao_academia.dto;

import org.springframework.validation.FieldError;

public record DadosErroValidacaoDTO(String campo, String mensagem) {
    public DadosErroValidacaoDTO(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}