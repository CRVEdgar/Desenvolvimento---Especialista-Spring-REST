package com.algafood.algafoodapiaplication.domain.service;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService1 {

    @Autowired
    private EstadoRepository1 estadoRepository1;

    public Estado salvar(Estado estado){
       return estadoRepository1.adicionarOuAtualizar(estado);

    }

    public void excluir(Long estadoId){
        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
        try {
            estadoRepository1.remover(estadoId);

        } catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException( String.format("O id [ %d ] da estado informado não existe", estadoId));

        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException( String.format("Estado de código %d não pode ser removido, pois está associado a outro item do banco", estadoId));
        }
    }
}
