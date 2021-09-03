package com.algafood.algafoodapiaplication.api.model.mixin;

import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
Classe de customização da representação dos recursos
CONFIGURADA POR: JacksonMixinModule
 */
public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
