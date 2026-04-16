package com.gestao_academia.controller;

import com.gestao_academia.model.Recepcionista;
import com.gestao_academia.model.Perfil;
import com.gestao_academia.model.Usuario;
import com.gestao_academia.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recepcionistas")
@CrossOrigin(origins = "http://localhost:5173")
public class RecepcionistaController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody Recepcionista recepcionista) { // Removi o @Valid aqui
        if (repository.existsByEmail(recepcionista.getEmail())) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado!");
        }

        recepcionista.setId(null);

        recepcionista.setSenha(encoder.encode(recepcionista.getSenha()));
        recepcionista.setTipo(Perfil.RECEPCIONISTA);
        recepcionista.setStatus("ATIVO");

        repository.save(recepcionista);
        return ResponseEntity.ok(recepcionista);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        var lista = repository.findAllByTipo(Perfil.RECEPCIONISTA);
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable UUID id) {
        var usuario = repository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid Recepcionista dados) {
        var opUsuario = repository.findById(id);
        if (opUsuario.isPresent()) {
            var usuario = opUsuario.get();
            usuario.setNome(dados.getNome());
            usuario.setEmail(dados.getEmail());

            if (dados.getSenha() != null && !dados.getSenha().isBlank()) {
                usuario.setSenha(encoder.encode(dados.getSenha()));
            }
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}