package com.algafood.algafoodapiaplication.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada") // - com essa anotação não será preciso capturar a exceção manualmente em cada método
public class EntidadeNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException (String mensagem){
        super(mensagem);
    }
}
