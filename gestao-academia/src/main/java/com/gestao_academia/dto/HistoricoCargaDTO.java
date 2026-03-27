package com.gestao_academia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricoCargaDTO(
        BigDecimal carga,
        LocalDate data,
        String nomeExercicio
) {}
