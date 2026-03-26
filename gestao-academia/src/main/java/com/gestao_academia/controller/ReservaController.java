package com.gestao_academia.controller;

import com.gestao_academia.dto.ReservaDetalhamentoDTO;
import com.gestao_academia.model.Reserva;
import com.gestao_academia.service.ReservaService;
import org.apache.coyote.Response;
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
    public ResponseEntity<ReservaDetalhamentoDTO> criar (Reserva reserva) {
        var salva = service.reservarVaga(reserva);
        return ResponseEntity.ok(new ReservaDetalhamentoDTO(salva));
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

