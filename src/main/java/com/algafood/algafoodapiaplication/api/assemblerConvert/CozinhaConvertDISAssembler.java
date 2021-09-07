package com.algafood.algafoodapiaplication.api.assemblerConvert;


import com.algafood.algafoodapiaplication.api.model.input.CozinhaInput;
import com.algafood.algafoodapiaplication.api.model.input.EstadoInput;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* CLASSE RESPONSAVEL POR CONVERTER CozinhaInput em Cozinha
* */

@Component
public class CozinhaConvertDISAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInput cozinhaInput){

        return modelMapper.map(cozinhaInput, Cozinha.class);

    }

    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha){
                        // (origem , destino);
        modelMapper.map(cozinhaInput, cozinha);
    }

}
