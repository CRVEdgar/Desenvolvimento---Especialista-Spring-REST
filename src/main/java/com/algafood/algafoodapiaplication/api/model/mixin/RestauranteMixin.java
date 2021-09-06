package com.algafood.algafoodapiaplication.api.model.mixin;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Endereco;
import com.algafood.algafoodapiaplication.domain.model.FormaPagamento;
import com.algafood.algafoodapiaplication.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/*
Classe de customização da representação dos recursos
CONFIGURADA POR: JacksonMixinModule

STATUS: INUTILIZADA PORQUE A CONFIGURAÇÃO DE VISUALIZAÇÃO ESTÁ SENDO FEITA PELA CLASSE RestauranteDTO
 */

//public class RestauranteMixin {
//
//
//    @JsonIgnoreProperties(value = "nome", allowGetters = true) //ignora (desconsidera) o recebimento do parametro //allowGetters = nao vai ignorar quando for feito um get em nomes(titulo)
//    private Cozinha cozinha;
//
//    @JsonIgnore
//    private List<FormaPagamento> formaPagamento = new ArrayList<>();
//
//    @JsonIgnore
//    private Endereco endereco;
//
//    //@JsonIgnore
//    private OffsetDateTime dataCadastro;
//
//    //@JsonIgnore
//    private OffsetDateTime dataAtualizacao;
//
//    @JsonIgnore
//    private List<Produto> produtos = new ArrayList<>();
//
//}
