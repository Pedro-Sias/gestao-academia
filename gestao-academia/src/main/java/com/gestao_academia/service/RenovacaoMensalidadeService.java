package com.gestao_academia.service;

import com.gestao_academia.model.Aluno;
import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.model.StatusPagamento;
import com.gestao_academia.repository.AlunoRepository;
import com.gestao_academia.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class RenovacaoMensalidadeService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;


    @Scheduled(cron = "0 0 0 1 * *")
    public void rotinaDeCobrancaMensal() {
        var alunos = alunoRepository.findAll();

        for (Aluno aluno : alunos) {
            Mensalidade nova = new Mensalidade();
            nova.setAluno(aluno);
            nova.setValor(new BigDecimal("150.00"));
            nova.setDataVencimento(LocalDate.now().plusDays(10));
            nova.setStatus(StatusPagamento.PENDENTE);

            mensalidadeRepository.save(nova);
        }
    }
}
