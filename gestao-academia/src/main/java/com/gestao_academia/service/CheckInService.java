package com.gestao_academia.service;

import com.gestao_academia.model.CheckIn;
import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.repository.CheckInRepository;
import com.gestao_academia.repository.MensalidadeRepository; // Importa o de mensalidade!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInService{

    @Autowired
    private CheckInRepository repository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    public CheckIn registrar(CheckIn checkIn) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByAlunoId(checkIn.getAluno().getId());

        boolean temDivida = mensalidades.stream()
                .anyMatch(m ->"PENDENTE".equals(m.getStatus()));

        if (temDivida) {
            throw new RuntimeException("Acesso negado! Aluno com mensalidade pendente");
        }
        return repository.save(checkIn);

    }

    public List<CheckIn> listarTodos(){
        return repository.findAll();
    }
}