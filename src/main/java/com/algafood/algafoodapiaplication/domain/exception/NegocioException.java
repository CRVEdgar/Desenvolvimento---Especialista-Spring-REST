package com.algafood.algafoodapiaplication.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)//- com essa anotação não será preciso capturar a exceção manualmente em cada método
public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem){
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
