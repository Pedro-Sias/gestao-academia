package com.gestao_academia.service;

import com.gestao_academia.model.Exercicio;
import com.gestao_academia.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ExercicioService {
    @Autowired
    private ExercicioRepository repository;

    public Exercicio salvar(Exercicio exercicio) {
        return repository.save(exercicio);
    }

    public List<Exercicio> listarTodos() {
        return repository.findAll();
    }


    public Exercicio buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado!"));
    }
}