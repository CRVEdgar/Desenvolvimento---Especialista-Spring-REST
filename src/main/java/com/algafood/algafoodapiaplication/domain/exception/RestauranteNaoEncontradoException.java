package com.algafood.algafoodapiaplication.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId){
        this(String.format("NÃO EXISTE UM CADASTRO DE RESTAURANTE COM CÓDIGO %d", restauranteId));
    }
}
