package com.algafood.algafoodapiaplication.api.assemblerConvert;

import com.algafood.algafoodapiaplication.api.model.CidadeDTO;
import com.algafood.algafoodapiaplication.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
 CLASSE UTILIZADA PARA CONVERTER Cidade em CidadeDTO
* */

@Component
public class CidadeConvertAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toDTO(Cidade cidade) {
                                //origem / destino
        return modelMapper.map(cidade, CidadeDTO.class);

    }

    public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades){

        return cidades.stream()
                .map(cidade -> toDTO(cidade))
                .collect(Collectors.toList());

    }

}
