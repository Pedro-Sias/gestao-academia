package com.gestao_academia.controller;

import com.gestao_academia.dto.TreinoDetalhamentoDTO;
import com.gestao_academia.model.Treino;
import com.gestao_academia.repository.AlunoRepository;
import com.gestao_academia.repository.ExercicioRepository;
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

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @PostMapping
    public ResponseEntity<TreinoDetalhamentoDTO> criar(@RequestBody Treino treino) {

        if (treino.getAluno() != null && treino.getAluno().getId() != null) {
            var alunoReal = alunoRepository.findById(treino.getAluno().getId())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            treino.setAluno(alunoReal);
        }

        if (treino.getSeries() != null) {
            treino.getSeries().forEach(serie -> {
                if (serie.getExercicio() != null && serie.getExercicio().getId() != null) {
                    var exercicioReal = exercicioRepository.findById(serie.getExercicio().getId())
                            .orElseThrow(() -> new RuntimeException("Exercício não encontrado: " + serie.getExercicio().getId()));
                    serie.setExercicio(exercicioReal);
                }
            });
        }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}