package com.gestao_academia.controller;

import com.gestao_academia.model.HistoricoCarga;
import com.gestao_academia.service.HistoricoCargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/historico-cargas")
public class HistoricoCargaController {

    @Autowired
    private HistoricoCargaService service;

    @PostMapping
    public ResponseEntity<HistoricoCarga> salvar(@RequestBody HistoricoCarga historico) {
        HistoricoCarga salvo = service.salvar(historico);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<HistoricoCarga>> listarPorAluno(@PathVariable UUID alunoId) {
        return ResponseEntity.ok(service.listarPorAluno(alunoId));
    }

    @GetMapping("/ultima-carga")
    public ResponseEntity<HistoricoCarga> buscarUltima(
            @RequestParam UUID alunoId,
            @RequestParam UUID exercicioId) {
        return ResponseEntity.ok(service.buscarUltimaCarga(alunoId, exercicioId));
    }
}