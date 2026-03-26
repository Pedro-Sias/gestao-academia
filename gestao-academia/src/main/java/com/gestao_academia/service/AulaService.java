package com.gestao_academia.service;
import com.gestao_academia.model.Aula;
import com.gestao_academia.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AulaService {

    @Autowired
    private AulaRepository repository;

    public Aula salvar(Aula aula) {

        if (aula.getProfessor() == null || aula.getProfessor().getId() == null) {
            throw new RuntimeException("Não pode criar aula sem um professor.");
        }
        return repository.save(aula);
    }

    public List<Aula> listarTodas(){
        return repository.findAll();
    }
}
