package com.algafood.algafoodapiaplication.api.model.mixin;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Endereco;
import com.algafood.algafoodapiaplication.domain.model.FormaPagamento;
import com.algafood.algafoodapiaplication.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
Classe de customização da representação dos recursos
CONFIGURADA POR: JacksonMixinModule
 */

public class RestauranteMixin {


    @JsonIgnoreProperties(value = "nome", allowGetters = true) //ignora (desconsidera) o recebimento do parametro //allowGetters = nao vai ignorar quando for feito um get em nomes(titulo)
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaPagamento> formaPagamento = new ArrayList<>();

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

}
