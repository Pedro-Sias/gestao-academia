package com.gestao_academia.controller;

import com.gestao_academia.dto.ReservaDetalhamentoDTO;
import com.gestao_academia.dto.ReservaRequestDTO;
import com.gestao_academia.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity<ReservaDetalhamentoDTO> criar(@RequestBody ReservaRequestDTO dados) {
        var reservaSalva = service.reservarVaga(dados);
        return ResponseEntity.ok(new ReservaDetalhamentoDTO(reservaSalva));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDetalhamentoDTO>> listar() {
        var lista = service.listarTodas().stream()
                .map(ReservaDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id){
        service.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}