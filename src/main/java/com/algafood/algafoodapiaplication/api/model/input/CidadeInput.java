package com.algafood.algafoodapiaplication.api.model.input;

/*
 CLASSE UTILIZADA PARA RECEBER O CONTEUDO DA REQUISIÇÃO
* */

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInput {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
