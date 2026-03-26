package com.gestao_academia.controller;
import com.gestao_academia.dto.CheckInDetalhamentoDTO;
import com.gestao_academia.model.CheckIn;
import com.gestao_academia.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/checkins")
public class CheckInController {
    @Autowired
    private CheckInService service;

  @PostMapping
  public ResponseEntity<CheckInDetalhamentoDTO> fazerCheckIn(@RequestBody CheckIn checkIn){
      var salvo = service.registrar(checkIn);
      return ResponseEntity.ok(new CheckInDetalhamentoDTO(salvo));
  }
    @GetMapping
    public ResponseEntity<List<CheckInDetalhamentoDTO>> listar() {
        var lista = service.listarTodos().stream()
                .map(CheckInDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}