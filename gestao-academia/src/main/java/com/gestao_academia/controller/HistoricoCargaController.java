package com.gestao_academia.controller;
import com.gestao_academia.model.HistoricoCarga;
import com.gestao_academia.service.HistoricoCargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/historico-cargas")
public class HistoricoCargaController {

    @Autowired
    private HistoricoCargaService service;


    @PostMapping
    public HistoricoCarga registrar(@RequestBody HistoricoCarga historico) {
        return service.salvar(historico);
    }

    @GetMapping("/ultima-carga")
    public HistoricoCarga getUltimaCarga(@RequestParam UUID alunoId, @RequestParam UUID exercicioId) {
        return service.buscarUltimaCarga(alunoId, exercicioId);
    }
}
