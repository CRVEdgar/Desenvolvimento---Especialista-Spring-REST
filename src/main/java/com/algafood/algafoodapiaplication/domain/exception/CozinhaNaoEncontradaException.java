package com.algafood.algafoodapiaplication.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public CozinhaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId){
        this(String.format("NÃO EXISTE UM CADASTRO DE COZINHA COM CÓDIGO %d", cozinhaId));
    }
}
