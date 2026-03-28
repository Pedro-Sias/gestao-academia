package com.gestao_academia.config;

import com.gestao_academia.dto.DadosErroValidacaoDTO;
import com.gestao_academia.dto.ErroResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Hidden
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponseDTO> tratarErroIntegridade (DataIntegrityViolationException ex){
        return ResponseEntity.badRequest().body(new ErroResponseDTO("Violação de dados: Verifique se os IDs informados (Aluno, Exercício, etc) são válidos."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacaoDTO>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacaoDTO::new ).toList());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponseDTO> tratarErroRegraDeNegocio(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new ErroResponseDTO(ex.getMessage()));
    }

}