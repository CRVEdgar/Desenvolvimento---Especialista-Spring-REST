package com.algafood.algafoodapiaplication.api.exceptionhandler;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/*
* PONTO CENTRAL PARA TRATAMENTO DE EXCEPTONHANDLER
* */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        Problem problem = createProblemBuilder(status, problemType, detail).build();

//        Problem problem = Problem.builder()
//                .status(status.value())
//                .type("http://algafood.com.br/entidade-nao-encontrada")
//                .title("ENTIDADE NAO ENCONTRADA")
//                .detail(ex.getMessage()).build();



        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem(e.getMessage())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(
            EntidadeEmUsoException ex, WebRequest request){

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);


//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem(e.getMessage())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(
            NegocioException ex, WebRequest request){

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem(e.getMessage())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
             HttpStatus status, WebRequest request) {
        if(body == null){
            body = Problem.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .build();
        }else if (body instanceof String){
            body = Problem.builder()
                .title((String) body)
                .status(status.value())
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder( HttpStatus status,
                ProblemType problemType, String detail){
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    //    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> tratarHttpMEdiaTypeNotSupportedException(){
//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem("Tipo de midia não suportado! ").build();
//
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
//    }
}
