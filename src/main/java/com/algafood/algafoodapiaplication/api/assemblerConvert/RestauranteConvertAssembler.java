package com.algafood.algafoodapiaplication.api.assemblerConvert;

import com.algafood.algafoodapiaplication.api.model.CozinhaDTO;
import com.algafood.algafoodapiaplication.api.model.RestauranteDTO;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
 CLASSE UTILIZADA PARA CONVERTER Restaurante em RestauranteDTO
* */

@Component
public class RestauranteConvertAssembler {

    @Autowired
    private ModelMapper modelMapper; //configurado por: package com.algafood.algafoodapiaplication.core.modelmapper;

    public RestauranteDTO toDTO(Restaurante restaurante) {

                                //origem / destino
        return modelMapper.map(restaurante, RestauranteDTO.class);

        //================SEM O MODEL MAPPER:

//        CozinhaDTO cozinhaDTO = new CozinhaDTO(); //CONVERSÃO DA ENTIDADE Cozinha para CozinhaDTO
//        cozinhaDTO.setId(restaurante.getCozinha().getId());
//        cozinhaDTO.setNome(restaurante.getCozinha().getNome());
//
//        RestauranteDTO restauranteDTO = new RestauranteDTO(); //CONVERSÃO DA ENTIDADE Restaurante para RestauranteDTO
//        restauranteDTO.setId(restaurante.getId());
//        restauranteDTO.setNome(restaurante.getNome());
//        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
//        restauranteDTO.setCozinha(cozinhaDTO);
//        return restauranteDTO;
    }

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes){

        return restaurantes.stream()
                .map(restaurante -> toDTO(restaurante))
                .collect(Collectors.toList());

        //FAZENDO COM LAÇO FOREACH
// List<RestauranteDTO> restauranteDTO = new ArrayList<>();
//        for(RestauranteDTO res: restauranteDTO){
//            res.setId(restaurantes.getId());
//            restauranteDTO.setId(res.getId());
//        }
    }

}
