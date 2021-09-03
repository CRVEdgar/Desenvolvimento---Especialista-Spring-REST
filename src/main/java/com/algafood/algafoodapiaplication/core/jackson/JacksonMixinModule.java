package com.algafood.algafoodapiaplication.core.jackson;

import com.algafood.algafoodapiaplication.api.model.mixin.CidadeMixin;
import com.algafood.algafoodapiaplication.api.model.mixin.RestauranteMixin;
import com.algafood.algafoodapiaplication.domain.model.Cidade;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);

    }

}
