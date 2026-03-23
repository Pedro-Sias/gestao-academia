package com.gestao_academia.service;

import com.gestao_academia.model.HistoricoCarga;
import com.gestao_academia.repository.HistoricoCargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class HistoricoCargaService {

    @Autowired
    private HistoricoCargaRepository repository;

    public HistoricoCarga salvar(HistoricoCarga historico) {
        return repository.save(historico);
    }

    public List<HistoricoCarga> listarPorAluno(UUID alunoId) {
        return repository.findByAlunoId(alunoId);
    }

    public HistoricoCarga buscarUltimaCarga(UUID alunoId, UUID exercicioId) {
        return repository.findAll().stream()
                .filter(h -> h.getAluno().getId().equals(alunoId))
                .filter(h ->h.getExercicio().getId().equals(exercicioId))
                .sorted((h1, h2) -> h2.getDataRegistro().compareTo(h1.getDataRegistro()))
                .findFirst()
                .orElse(null);
    }
}