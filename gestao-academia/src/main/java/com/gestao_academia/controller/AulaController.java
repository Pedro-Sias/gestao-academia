package com.gestao_academia.controller;
import com.gestao_academia.dto.AulaDetalhamentoDTO;
import com.gestao_academia.model.Aula;
import com.gestao_academia.repository.AulaRepository;
import com.gestao_academia.service.AulaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaService service;

    @PostMapping
    public ResponseEntity<AulaDetalhamentoDTO> criar(@RequestBody Aula aula) {
        var salva = service.salvar(aula);

        return ResponseEntity.ok(new AulaDetalhamentoDTO(salva));
    }

    @GetMapping
    public ResponseEntity<List<AulaDetalhamentoDTO>> listar() {
        var lista = service.listarTodas().stream()
                .map(AulaDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

}
