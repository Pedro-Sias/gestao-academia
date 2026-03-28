package com.gestao_academia.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name ="professor")
@Data
public class Professor extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String nome;
    private String especialidade;
    private String registroProfissional;



}
