package com.algafood.algafoodapiaplication.api.model.mixin;

import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();
}
