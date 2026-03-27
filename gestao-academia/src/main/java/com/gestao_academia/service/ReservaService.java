package com.gestao_academia.service;
import com.gestao_academia.dto.ReservaRequestDTO;
import com.gestao_academia.model.Aula;
import com.gestao_academia.model.Reserva;
import com.gestao_academia.model.StatusPagamento;
import com.gestao_academia.repository.AlunoRepository;
import com.gestao_academia.repository.AulaRepository;
import com.gestao_academia.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Reserva reservarVaga(ReservaRequestDTO dados) {
        Aula aula = aulaRepository.findById(dados.aulaId())
                .orElseThrow(() -> new RuntimeException("Aula não encontrada!"));

        var aluno = alunoRepository.findById(dados.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));


        if (aula.getVagas() <= 0) {
            throw new RuntimeException("Aula lotada, parceiro!");
        }

         if (aluno.getMensalidades().stream().anyMatch(m -> m.getStatus() != StatusPagamento.PAGO)) {
             throw new RuntimeException("Regularize sua mensalidade para reservar aulas!");
         }


        aula.setVagas(aula.getVagas() - 1);
        aulaRepository.save(aula);


        Reserva reserva = new Reserva();
        reserva.setAluno(aluno);
        reserva.setAula(aula);
        reserva.setStatus("CONFIRMADA");

        return repository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(UUID reservaId) {
        Reserva reserva = repository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        Aula aula = reserva.getAula();

        aula.setVagas(aula.getVagas() + 1);
        aulaRepository.save(aula);

        repository.delete(reserva);
    }

    public List<Reserva> listarTodas() {
        return repository.findAll();
    }
}