package com.algafood.algafoodapiaplication.api.model.mixin;

import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class ProdutoMixin {

    @JsonIgnore
    private Restaurante restaurante;
}
