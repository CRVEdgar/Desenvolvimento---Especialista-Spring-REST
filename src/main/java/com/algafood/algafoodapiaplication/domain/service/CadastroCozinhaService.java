package com.algafood.algafoodapiaplication.domain.service;

import com.algafood.algafoodapiaplication.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadastroCozinhaService {

    private static final String MSG_COZINHA_EM_USO
            = "Cozinha de código %d não pode ser removida, pois está associado a outro item do banco";

//    private static final String MSG_COZINHA_NAO_ENCONTRADA
//            = "ID INVALIDO - ID [ %d ] DA COZINHA INDICADA NÃO ENCONTRADO";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
       return cozinhaRepository.save(cozinha);

    }

    @Transactional
    public void excluir(Long cozinhaId){
        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
        try {
            cozinhaRepository.deleteById(cozinhaId);
            cozinhaRepository.flush(); //descarrega todas as mudanças penddentes no banco de dados -> pra poder capturar a exceção lançada neste metodo [excluir]

        } catch(EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(cozinhaId);

        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException( String.format(MSG_COZINHA_EM_USO, cozinhaId));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }
}
