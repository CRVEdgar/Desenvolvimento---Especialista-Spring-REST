package com.algafood.algafoodapiaplication.api.assemblerConvert;

import com.algafood.algafoodapiaplication.api.model.CozinhaDTO;
import com.algafood.algafoodapiaplication.api.model.EstadoDTO;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
 CLASSE UTILIZADA PARA CONVERTER Cozinha em CozinhaDTO
* */

@Component
public class CozinhaConvertAssembler {

    @Autowired
    private ModelMapper modelMapper; //configurado por: package com.algafood.algafoodapiaplication.core.modelmapper;


    public CozinhaDTO toDTO(Cozinha cozinha) {
                                //origem / destino
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toCollectionDTO(List<Cozinha> cozinhas){

        return cozinhas.stream()
                .map(cozinha -> toDTO(cozinha))
                .collect(Collectors.toList());

    }

}
