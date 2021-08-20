package com.algafood.algafoodapiaplication.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long cidadeId){
        this(String.format("NÃO EXISTE UM CADASTRO DE CIDADE COM CÓDIGO %d", cidadeId));
    }
}
