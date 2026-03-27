package com.gestao_academia.controller;

import com.gestao_academia.model.Aluno;
import com.gestao_academia.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/resumo")
    public Map<String, Object> buscarResumo() {

        return Map.of(
                "totalAlunos", service.contarAlunosAtivos(),
                "quantidadeInadimplentes", service.listarAlunosInadimplentes().size(),
                "faturamentoTotal", service.calcularFaturamentoTotal() // <--- Chama o cálculo de R$
        );
    }

    @GetMapping("/inadimplentes")
    public List<Aluno> listarInadimplentes() {

        return service.listarAlunosInadimplentes();
    }

    @GetMapping("/frequencia/{alunoId}")
    public Map<String, Long> getFrequencia(@PathVariable UUID alunoId) {
        return service.buscarFrequenciaSemanal(alunoId);
    }
}