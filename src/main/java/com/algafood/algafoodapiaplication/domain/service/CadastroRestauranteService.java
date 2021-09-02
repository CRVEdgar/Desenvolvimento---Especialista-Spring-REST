package com.algafood.algafoodapiaplication.domain.service;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.RestauranteNaoEncontradoException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import com.algafood.algafoodapiaplication.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO
            = "Cozinha de código %d não pode ser removida, pois está associado a outro item do banco";

//    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO
//            = "ID INVALIDO - ID [ %d ] DO RESTAURANTE INDICADO NÃO ENCONTRADO";
//
//    private static final String MSG_COZINHA_NAO_ENCONTRADA
//            = "ID INVALIDO - ID [ %d ] DA COZINHA INDICADA NÃO ENCONTRADA";

    @Autowired
    private RestauranteRepository restauranteRepository;

//    @Autowired
//    private CozinhaRepository cozinhaRepository;

    @Autowired
    CadastroCozinhaService cadastroCozinha;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
//        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
//                .orElseThrow( () -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);

    }

    @Transactional
    public void excluir(Long restauranteId){
        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
        try {
            restauranteRepository.deleteById(restauranteId);

        } catch(EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException( restauranteId);

        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException( String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException( restauranteId));
    }
}
