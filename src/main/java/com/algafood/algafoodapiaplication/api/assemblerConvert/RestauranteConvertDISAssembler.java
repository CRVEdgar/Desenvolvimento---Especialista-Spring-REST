package com.algafood.algafoodapiaplication.api.assemblerConvert;


import com.algafood.algafoodapiaplication.api.model.input.RestauranteInput;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.springframework.stereotype.Component;

/*
* CLASSE RESPONSAVEL POR CONVERTER RestauranteInput em Restaurante
* */

@Component
public class RestauranteConvertDISAssembler {

    // metodo que recebe um Restaurante enviado na requisição e converte para um tipo Restaurante do dominio
    public Restaurante toDomainObject(RestauranteInput restauranteInput){

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }

}
