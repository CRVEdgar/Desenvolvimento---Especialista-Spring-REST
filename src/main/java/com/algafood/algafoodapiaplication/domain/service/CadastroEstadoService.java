package com.algafood.algafoodapiaplication.domain.service;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EstadoNaoEncontradoException;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO
            = "Estado de código %d não pode ser removido, pois está associado a outro item do banco";

//    private static final String MSG_ESTADO_NAO_ENCONTRADO
//            = "ID INVALIDO - ID [ %d ] DO ESTADO INDICADO NÃO ENCONTRADO";

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado){
       return estadoRepository.save(estado);

    }

    @Transactional
    public void excluir(Long estadoId){
        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
        try {
            estadoRepository.deleteById(estadoId);

        } catch(EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException( estadoId);

        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException( String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscarOuFalhar(Long estadoId){
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }
}
