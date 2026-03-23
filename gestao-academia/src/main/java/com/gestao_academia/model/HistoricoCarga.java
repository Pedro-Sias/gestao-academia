package com.gestao_academia.model;
import com.gestao_academia.repository.ExercicioRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historico")
@Data
public class HistoricoCarga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String cargaAnterior;
    private String cargaAtual;
    private LocalDateTime dataRegistro = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;

    private String observacao;
}
