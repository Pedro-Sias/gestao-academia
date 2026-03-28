package com.gestao_academia.service;

import com.gestao_academia.model.Professor;
import com.gestao_academia.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Professor salvar(Professor professor) {
        professor.setSenha(passwordEncoder.encode(professor.getSenha()));
        return repository.save(professor);
    }

    public List<Professor> listarTodos() {
        return repository.findAll();
    }
}