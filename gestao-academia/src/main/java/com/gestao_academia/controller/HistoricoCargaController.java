package com.gestao_academia.controller;

import com.gestao_academia.dto.HistoricoCargaDTO;
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
    public ResponseEntity<HistoricoCargaDTO> buscarUltima(
            @RequestParam UUID alunoId,
            @RequestParam UUID exercicioId) {

        var historico = service.buscarUltimaCarga(alunoId, exercicioId);

        if (historico == null) {
            return ResponseEntity.ok(new HistoricoCargaDTO(java.math.BigDecimal.ZERO, java.time.LocalDate.now(), "Sem registro"));
        }


        var dto = new HistoricoCargaDTO(
                historico.getCargaAtual(),
                historico.getDataRegistro().toLocalDate(),
                historico.getExercicio() != null ? historico.getExercicio().getNome() : "Exercicio não identificado"
        );

        return ResponseEntity.ok(dto);
    }
}