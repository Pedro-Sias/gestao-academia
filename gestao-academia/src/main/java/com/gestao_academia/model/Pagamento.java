package com.gestao_academia.model;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.grammars.hql.HqlParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
@Data
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime dataPagamento;
    private String formaPagamento;

    @OneToOne
    @JoinColumn(name = "mensalidade_id")
    private Mensalidade mensalidade;


}
