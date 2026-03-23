package com.gestao_academia.controller;
import com.gestao_academia.model.Aluno;
import com.gestao_academia.service.AlunoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno){
        return ResponseEntity.ok(service.salvar(aluno));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar()  {

        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
