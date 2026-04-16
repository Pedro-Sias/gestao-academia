package com.gestao_academia.repository;

import com.gestao_academia.model.Usuario;
import com.gestao_academia.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByTipo(Perfil tipo);

    boolean existsByEmail(String email);

    long countByTipo(Perfil tipo);
}