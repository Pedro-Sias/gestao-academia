package com.gestao_academia.controller;
import com.gestao_academia.dto.PagamentoDetalhamentoDTO;
import com.gestao_academia.model.Pagamento;
import com.gestao_academia.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<PagamentoDetalhamentoDTO> cadastrar(@RequestBody Pagamento pagamento) {

        var salvo = service.registrarPagamento(pagamento);
        return ResponseEntity.ok(new PagamentoDetalhamentoDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDetalhamentoDTO>> listar() {

        var lista = service.listarTodos().stream()
                .map(PagamentoDetalhamentoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}
