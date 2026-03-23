package com.gestao_academia.service;
import com.gestao_academia.model.Mensalidade;
import com.gestao_academia.model.Pagamento;
import com.gestao_academia.repository.MensalidadeRepository;
import com.gestao_academia.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;



@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Transactional
    public Pagamento registrarPagamento(Pagamento pagamento){
        Mensalidade mensalidade = mensalidadeRepository.findById(pagamento.getMensalidade().getId())
                .orElseThrow(() -> new RuntimeException("Mensalidade não encontrada"));

        mensalidade.setStatus("PAGO");
        mensalidadeRepository.save(mensalidade);

        pagamento.setDataPagamento(LocalDateTime.now());

        return repository.save(pagamento);
    }

}
