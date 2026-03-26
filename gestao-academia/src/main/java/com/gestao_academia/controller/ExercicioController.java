package com.gestao_academia.controller;
import com.gestao_academia.dto.ExercicioDetalhamentoDTO;
import com.gestao_academia.model.Exercicio;
import com.gestao_academia.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {
    @Autowired
    private ExercicioService service;

    @PostMapping
    public ResponseEntity<ExercicioDetalhamentoDTO> cadastrar(@RequestBody Exercicio exercicio) {
        var salvo = service.salvar(exercicio);
        return ResponseEntity.ok(new ExercicioDetalhamentoDTO(salvo));
    }
    @GetMapping
    public ResponseEntity<List<ExercicioDetalhamentoDTO>> listar() {
        var lista = service.listarTodos().stream()
                .map(ExercicioDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}
