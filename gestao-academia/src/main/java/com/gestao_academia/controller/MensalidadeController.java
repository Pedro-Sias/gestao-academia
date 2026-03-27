package com.gestao_academia.controller;

import com.gestao_academia.dto.MensalidadeDetalhamentoDTO;
import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.service.MensalidadeService; // Importa o Service novo
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

@RestController
@RequestMapping("/api/mensalidades")
public class MensalidadeController {

    @Autowired
    private MensalidadeService service;


    @PostMapping
    public ResponseEntity<MensalidadeDetalhamentoDTO> criar (@RequestBody Mensalidade mensalidade){
        var salva = service.salvar(mensalidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MensalidadeDetalhamentoDTO(salva));
    }

    @GetMapping
    public ResponseEntity<List<MensalidadeDetalhamentoDTO>> listarTodos() {
        var lista = service.listarTodas().stream()
                .map(MensalidadeDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/inadimplentes")
    public ResponseEntity<List<MensalidadeDetalhamentoDTO>> getInadimplentes() {
        var lista = service.relatorioInadimplentes().stream()
                .map(MensalidadeDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<MensalidadeDetalhamentoDTO> pagar(@PathVariable UUID id) {
        var mensalidadePaga = service.confirmarPagamento(id);
        return ResponseEntity.ok(new MensalidadeDetalhamentoDTO(mensalidadePaga));
    }

}