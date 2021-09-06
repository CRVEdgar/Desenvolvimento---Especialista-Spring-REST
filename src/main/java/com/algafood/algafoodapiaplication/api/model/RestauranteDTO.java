package com.algafood.algafoodapiaplication.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 CLASSE UTILIZADA PARA FORNECER O CONTEUDO DA REQUISIÇÃO
* */

@Setter
@Getter
public class RestauranteDTO {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;

}
