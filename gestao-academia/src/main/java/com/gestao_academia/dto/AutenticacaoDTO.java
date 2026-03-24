package com.gestao_academia.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
        @NotBlank String email,
        @NotBlank String senha
) {
}