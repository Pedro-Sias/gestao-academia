package com.gestao_academia.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "checkins")
@Data
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private LocalDateTime dataCheckin = LocalDateTime.now();
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
