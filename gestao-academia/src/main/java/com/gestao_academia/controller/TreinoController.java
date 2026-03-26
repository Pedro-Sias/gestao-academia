package com.gestao_academia.controller;
import com.gestao_academia.dto.TreinoDetalhamentoDTO;
import com.gestao_academia.model.Treino;
import com.gestao_academia.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/treinos")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")
public class TreinoController {

    @Autowired
    private TreinoService service;

    @PostMapping
    public ResponseEntity<TreinoDetalhamentoDTO> criar(@RequestBody Treino treino) {
        var salvo = service.salvar(treino);
        return ResponseEntity.ok(new TreinoDetalhamentoDTO(salvo));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<TreinoDetalhamentoDTO>> listarPorAluno(@PathVariable UUID alunoId) {
        var lista = service.listarPorAluno(alunoId).stream()
                .map(TreinoDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}