package com.algafood.algafoodapiaplication.api.assemblerConvert;

import com.algafood.algafoodapiaplication.api.model.CozinhaDTO;
import com.algafood.algafoodapiaplication.api.model.RestauranteDTO;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteConvertAssembler {

    public RestauranteDTO toDTO(Restaurante restaurante) {
        CozinhaDTO cozinhaDTO = new CozinhaDTO(); //CONVERSÃO DA ENTIDADE Cozinha para CozinhaDTO
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO restauranteDTO = new RestauranteDTO(); //CONVERSÃO DA ENTIDADE Restaurante para RestauranteDTO
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
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
