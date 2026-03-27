package com.gestao_academia.dto;

import java.util.UUID;

public record ReservaRequestDTO(UUID alunoId, UUID aulaId) {
}