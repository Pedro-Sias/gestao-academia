package com.gestao_academia.controller;
import com.gestao_academia.dto.ProfessorDetalhamentoDTO;
import com.gestao_academia.model.Professor;
import com.gestao_academia.repository.ProfessorRepository;
import com.gestao_academia.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @PostMapping
    public ResponseEntity<ProfessorDetalhamentoDTO> cadastrar (@RequestBody Professor professor){
        var salvo = service.salvar(professor);
        return ResponseEntity.ok(new ProfessorDetalhamentoDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDetalhamentoDTO>> listar(){
        var lista = service.listarTodos().stream()
                .map(ProfessorDetalhamentoDTO:: new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}
