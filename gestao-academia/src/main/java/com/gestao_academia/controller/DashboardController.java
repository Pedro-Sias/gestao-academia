package com.gestao_academia.controller;

import com.gestao_academia.model.Perfil;
import com.gestao_academia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        long totalAlunos = repository.countByTipo(Perfil.ALUNO);

        stats.put("alunosAtivos", totalAlunos);
        stats.put("faturamento", totalAlunos * 89.90);
        stats.put("checkins", totalAlunos > 0 ? 1 : 0);
        stats.put("metas", 100);

        return ResponseEntity.ok(stats);
    }
}