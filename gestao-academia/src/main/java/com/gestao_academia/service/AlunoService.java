package com.gestao_academia.service;

import com.gestao_academia.model.Aluno;
import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.model.StatusPagamento;
import com.gestao_academia.model.Perfil;
import com.gestao_academia.repository.AlunoRepository;
import com.gestao_academia.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Aluno salvar(Aluno aluno) {

        String senhaCriptografada = passwordEncoder.encode(aluno.getSenha());
        aluno.setSenha(senhaCriptografada);


        aluno.setTipo(Perfil.ALUNO);


        Aluno alunoSalvo = repository.save(aluno);

        Mensalidade mensalidadeInicial = new Mensalidade();
        mensalidadeInicial.setAluno(alunoSalvo);
        mensalidadeInicial.setValor(BigDecimal.valueOf(150.00));
        mensalidadeInicial.setDataVencimento((LocalDate.now().plusDays(5)));
        mensalidadeInicial.setStatus(StatusPagamento.PENDENTE);

        mensalidadeRepository.save(mensalidadeInicial);

        return alunoSalvo;
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}