package com.gestao_academia.model;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mensalidades")
@Data
public class Mensalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal valor;
    private LocalDate dataVencimento;
    private String status;


    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
