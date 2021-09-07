package com.algafood.algafoodapiaplication.api.assemblerConvert;


import com.algafood.algafoodapiaplication.api.model.input.EstadoInput;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* CLASSE RESPONSAVEL POR CONVERTER EstadoInput em Estado
* */

@Component
public class EstadoConvertDISAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // metodo que recebe um EstadoInput enviado na requisição e converte para um tipo Estado do dominio
    public Estado toDomainObject(EstadoInput estadoInput){

        return modelMapper.map(estadoInput, Estado.class);

    }

    public void copyToDomainObject(EstadoInput estadoInput, Estado estado){
                        // (origem , destino);
        modelMapper.map(estadoInput, estado);
    }

}
