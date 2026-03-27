package com.gestao_academia.service;

import com.gestao_academia.model.HistoricoCarga;
import com.gestao_academia.repository.HistoricoCargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class HistoricoCargaService {

    @Autowired
    private HistoricoCargaRepository repository;


    public HistoricoCarga salvar(HistoricoCarga novo) {

        var ultimaCarga = repository.findFirstByAlunoIdAndExercicioIdOrderByDataRegistroDesc(
                novo.getAluno().getId(),
                novo.getExercicio().getId()
        );


        BigDecimal cargaPassada = ultimaCarga
                .map(HistoricoCarga::getCargaAtual)
                .orElse(BigDecimal.ZERO);

        novo.setCargaAnterior(cargaPassada);

        return repository.save(novo);
    }

    public List<HistoricoCarga> listarPorAluno(UUID alunoId) {
        return repository.findByAlunoId(alunoId);
    }


    public HistoricoCarga buscarUltimaCarga(UUID alunoId, UUID exercicioId) {
        return repository.findFirstByAlunoIdAndExercicioIdOrderByDataRegistroDesc(alunoId, exercicioId)
                .orElse(null);
    }
}