package com.algafood.algafoodapiaplication.api.assemblerConvert;


import com.algafood.algafoodapiaplication.api.model.input.RestauranteInput;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* CLASSE RESPONSAVEL POR CONVERTER RestauranteInput em Restaurante
* */

@Component
public class RestauranteConvertDISAssembler {

    @Autowired
    private ModelMapper modelMapper; //configurado por: package com.algafood.algafoodapiaplication.core.modelmapper;

    // metodo que recebe um Restaurante enviado na requisição e converte para um tipo Restaurante do dominio
    public Restaurante toDomainObject(RestauranteInput restauranteInput){

        return modelMapper.map(restauranteInput, Restaurante.class);

        //================SEM O MODEL MAPPER:

//        Cozinha cozinha = new Cozinha();
//        cozinha.setId(restauranteInput.getCozinha().getId());
//
//        Restaurante restaurante = new Restaurante();
//        restaurante.setNome(restauranteInput.getNome());
//        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
//        restaurante.setCozinha(cozinha);
//
//        return restaurante;
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante){
        restaurante.setCozinha(new Cozinha()); //instacia uma nova cozinha para evitar que o id informado dê erro//evita que o JPA lance uma exception
                        // (origem , destino);
        modelMapper.map(restauranteInput, restaurante);
    }

}
