package com.gestao_academia.service;

import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.model.StatusPagamento;
import com.gestao_academia.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository repository;


    public Mensalidade salvar(Mensalidade mensalidade) {
        return repository.save(mensalidade);
    }


    public List<Mensalidade> relatorioInadimplentes() {
        return repository.findByStatusNot(StatusPagamento.PAGO);
    }

    public List<Mensalidade> listarTodas() {
        return repository.findAll();
    }
}