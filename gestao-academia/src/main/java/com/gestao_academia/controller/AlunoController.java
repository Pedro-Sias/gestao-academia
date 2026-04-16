package com.gestao_academia.controller;
import com.gestao_academia.dto.AlunoDetalhamentoDTO;
import com.gestao_academia.model.Aluno;
import com.gestao_academia.model.Perfil;
import com.gestao_academia.service.AlunoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AlunoDetalhamentoDTO> cadastrar(@RequestBody @Valid Aluno aluno) {
        aluno.setTipo(Perfil.ALUNO);

        var alunoSalvo = service.salvar(aluno);
        return ResponseEntity.ok(new AlunoDetalhamentoDTO(alunoSalvo));
    }
    @GetMapping
    public ResponseEntity<List<AlunoDetalhamentoDTO>> listar() {

        var lista = service.listarTodos().stream()
                .map(AlunoDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
