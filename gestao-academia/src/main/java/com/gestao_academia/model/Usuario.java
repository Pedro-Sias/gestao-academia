package com.gestao_academia.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name ="usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Data

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String email;

    @NotBlank(message = "A senha é obrigatória!")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil tipo;

    private String Status = "ATIVO";
}
