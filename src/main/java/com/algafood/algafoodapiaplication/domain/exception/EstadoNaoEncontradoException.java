package com.algafood.algafoodapiaplication.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId){
        this(String.format("NÃO EXISTE UM CADASTRO DE ESTADO COM CÓDIGO %d", estadoId));
    }
}
