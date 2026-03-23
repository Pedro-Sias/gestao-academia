package com.gestao_academia.service;

import com.gestao_academia.model.Aluno;
import com.gestao_academia.model.StatusPagamento;
import com.gestao_academia.repository.AlunoRepository;
import com.gestao_academia.repository.CheckInRepository;
import com.gestao_academia.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    public long contarAlunosAtivos() {
        return alunoRepository.count();
    }

    public List<Aluno> listarAlunosInadimplentes() {
        return mensalidadeRepository.findAll().stream()
                .filter(m -> m.getStatus() == StatusPagamento.ATRASADO)
                .map(m -> m.getAluno())
                .distinct()
                .collect(Collectors.toList());
    }


    public Map<String, Long> buscarFrequenciaSemanal(UUID alunoId) {
        LocalDateTime seteDiasAtras = LocalDateTime.now().minusDays(7);


        long totalCheckins = checkInRepository.findByAlunoIdAndDataCheckinAfter(alunoId, seteDiasAtras).size();

        return Map.of("checkinsUltimos7Dias", totalCheckins);
    }
}