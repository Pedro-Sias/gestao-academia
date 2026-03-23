package com.gestao_academia.controller;

import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensalidades")
public class MensalidadeController {

    @Autowired
    private MensalidadeRepository repository;

    @PostMapping
    public ResponseEntity<Mensalidade> criar(@RequestBody Mensalidade mensalidade) {
        // Aqui ele vai salvar a mensalidade no banco
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(mensalidade));
    }

    @GetMapping
    public List<Mensalidade> listarTodos() {
        return repository.findAll();
    }
}