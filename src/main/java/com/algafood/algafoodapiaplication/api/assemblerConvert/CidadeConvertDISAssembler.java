package com.algafood.algafoodapiaplication.api.assemblerConvert;


import com.algafood.algafoodapiaplication.api.model.input.CidadeInput;
import com.algafood.algafoodapiaplication.api.model.input.RestauranteInput;
import com.algafood.algafoodapiaplication.domain.model.Cidade;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* CLASSE RESPONSAVEL POR CONVERTER CidadeInput em Cidade
* */

@Component
public class CidadeConvertDISAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // metodo que recebe uma CidadeInput enviada na requisição e converte para um tipo Cidade do dominio
    public Cidade toDomainObject(CidadeInput cidadeInput){

        return modelMapper.map(cidadeInput, Cidade.class);

    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade){
        cidade.setEstado(new Estado()); //instacia um novo Estado para evitar que o id informado dê erro//evita que o JPA lance uma exception
                        // (origem , destino);
        modelMapper.map(cidadeInput, cidade);
    }

}
