package com.gestao_academia.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@Table(name= "alunos")
@Data
@EqualsAndHashCode(callSuper = true)

public class Aluno extends Usuario {

    private String matricula;

    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
    private List<Mensalidade> mensalidades;

    @PrePersist
    public void gerarMatricula() {
                this.matricula = "MAT" + java.time.Year.now().getValue() + (int)(Math.random() * 10000);
    }

}
