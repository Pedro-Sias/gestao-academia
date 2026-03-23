package com.gestao_academia.controller;
import com.gestao_academia.model.Pagamento;
import com.gestao_academia.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public Pagamento pagar(@RequestBody Pagamento pagamento){
        return service.registrarPagamento((pagamento));
    }


}
