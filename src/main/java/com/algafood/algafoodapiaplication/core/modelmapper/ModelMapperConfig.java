package com.algafood.algafoodapiaplication.core.modelmapper;

import com.algafood.algafoodapiaplication.api.model.RestauranteDTO;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
// modo customizado:
//    @Bean
//    public ModelMapper modelMapper(){
//
//        var modelMapper = new ModelMapper();
//
//        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
//            .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
//        return modelMapper;
//    }
}
