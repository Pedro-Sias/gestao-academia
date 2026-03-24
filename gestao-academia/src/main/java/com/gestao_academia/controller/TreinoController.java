package com.gestao_academia.controller;
import com.gestao_academia.dto.TreinoDetalhamentoDTO;
import com.gestao_academia.model.Treino;
import com.gestao_academia.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api/treinos")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")
public class TreinoController {
    @Autowired
    private TreinoService service;

    @PostMapping
    public Treino criar(@RequestBody Treino treino) {
        return service.salvar(treino);
    }

    @GetMapping
    public List<TreinoDetalhamentoDTO> listar() {
        return service.listarTodos().stream() // Troquei repository por service.listarTodos()
                .map(TreinoDetalhamentoDTO::new)
                .toList();
    }
}
