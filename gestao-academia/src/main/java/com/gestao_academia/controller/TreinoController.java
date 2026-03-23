package com.gestao_academia.controller;
import com.gestao_academia.model.Treino;
import com.gestao_academia.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;





@RestController
@RequestMapping("/api/treinos")
public class TreinoController {
    @Autowired
    private TreinoService service;

    @PostMapping
    public Treino criar(@RequestBody Treino treino) {
        return service.salvar(treino);
    }

    @GetMapping
    public List<Treino> listarTodos() {
        return service.listarTodos();
    }
}
