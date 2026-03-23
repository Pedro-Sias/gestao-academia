package com.gestao_academia.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name= "alunos")
@Data
@EqualsAndHashCode(callSuper = true)

public class Aluno extends Usuario {

    private String matricula;

    @PrePersist
    public void gerarMatricula() {
        // Gera uma matrícula com o prefixo MAT, o ano atual e um número aleatório
        this.matricula = "MAT" + java.time.Year.now().getValue() + (int)(Math.random() * 10000);
    }

}
