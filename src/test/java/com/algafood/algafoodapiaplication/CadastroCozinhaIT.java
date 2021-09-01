package com.algafood.algafoodapiaplication;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItems;

import com.algafood.algafoodapiaplication.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.service.CadastroCozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @BeforeAll
    public static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //auxilia no Debug mostrando o que foi realizado no metodo //habilita os logs quando o teste falhar
        RestAssured.port = port; //especifica a porta padrao
        RestAssured.basePath = "/cozinhas";
    }

    // TESTES DE INTEGRAÇÃO

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

    //----------------------------------------------------
     // TESTES DE API

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinha(){

            given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(200); // ou statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter4Cozinhas_QuandoConsultarCozinha(){

        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(4)); //valida se há 4 objetos [quantidade que há no banco]
    }

    @Test
    public void deveConterCozinhasEspecificadas_QuandoConsultarCozinha(){

        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("titulo", hasItems("Brasileira", "Australiana", "Tailandesa")); //valida se há as cozinhas especificadas
    }

}
