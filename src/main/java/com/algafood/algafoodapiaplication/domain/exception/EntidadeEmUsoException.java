package com.algafood.algafoodapiaplication.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.CONFLICT) //quando a entidade estiver em uso - com essa anotação não será preciso capturar a exceção manualmente em cada método
public class EntidadeEmUsoException extends NegocioException {
    private static final long serialVersionUID = 1L;

     public EntidadeEmUsoException(String mensagem){
         super(mensagem);
     }
}
