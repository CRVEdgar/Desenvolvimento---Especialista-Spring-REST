package com.algafood.algafoodapiaplication;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import com.algafood.algafoodapiaplication.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroCozinhaService;
import com.algafood.algafoodapiaplication.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    //private static Flyway flyway;
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

//    @Autowired
//    private Flyway flyway;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @BeforeEach
    private void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //auxilia no Debug mostrando o que foi realizado no metodo //habilita os logs quando o teste falhar
        RestAssured.port = port; //especifica a porta padrao
        RestAssured.basePath = "/cozinhas";
//        flyway.migrate(); //utilizado para restartar o BD a cada teste que for realizado

        databaseCleaner.clearTables(); // limpa os dados de todas as tabelas do banco
        prepararDados();
    }

    //----------------------------------------------------
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

//    @Test
//    public void deveFalhar_QuandoExcluirCozinhaEmUso(){
//        Assertions.assertThrows(EntidadeEmUsoException.class, () ->{
//            cadastroCozinha.excluir(1L);
//        });
//    }

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
    public void testeConter2Cozinhas_QuandoConsultarCozinha(){

        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(2)); //valida se há 2 objetos [quantidade que há no banco]
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

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        given()
            .body("{ \"titulo\": \"Chinesa\" }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        given()
            .pathParam("cozinhaId", 2)
            .accept(ContentType.JSON)
        .when()
            .get("/{cozinhaId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("titulo", equalTo("Australiana"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        given()
                .pathParam("cozinhaId", 100)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados(){
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Australiana");
        cozinhaRepository.save(cozinha2);
    }

}
