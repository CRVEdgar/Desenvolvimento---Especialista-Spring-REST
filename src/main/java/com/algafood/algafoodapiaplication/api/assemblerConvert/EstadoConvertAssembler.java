package com.algafood.algafoodapiaplication.api.assemblerConvert;

import com.algafood.algafoodapiaplication.api.model.EstadoDTO;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
 CLASSE UTILIZADA PARA CONVERTER Estado em EstadoDTO
* */

@Component
public class EstadoConvertAssembler {

    @Autowired
    private ModelMapper modelMapper; //configurado por: package com.algafood.algafoodapiaplication.core.modelmapper;


    public EstadoDTO toDTO(Estado estado) {
                                //origem / destino
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toCollectionDTO(List<Estado> estados){

        return estados.stream()
                .map(estado -> toDTO(estado))
                .collect(Collectors.toList());

    }

}
