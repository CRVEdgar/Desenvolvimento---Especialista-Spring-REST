package com.algafood.algafoodapiaplication;

import com.algafood.algafoodapiaplication.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void testarCadastroCozinhaComSucesso(){

        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Marroquina");

        // ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void testarCadastroCozinhaSemNome(){

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            Cozinha novaCozinha = new Cozinha();
            novaCozinha.setNome(" ");

            novaCozinha = cadastroCozinha.salvar(novaCozinha);
        });

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso(){
        Assertions.assertThrows(EntidadeEmUsoException.class, () ->{
            cadastroCozinha.excluir(1L);
        });
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente(){

        Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
            Cozinha cozinhaDEL = new Cozinha();
            cozinhaDEL.setId(10L);

            cadastroCozinha.excluir(cozinhaDEL.getId());
        });
    }

}
