package com.algafood.algafoodapiaplication.domain.service;

//import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
//import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
//import com.algafood.algafoodapiaplication.domain.model.Estado;
//import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CadastroEstadoService {
//
//    @Autowired
//    private EstadoRepository estadoRepository;
//
//    public Estado salvar(Estado estado){
//        return estadoRepository.adicionarOuAtualizar(estado);
//    }
//
//    public void excluir(Long estadoId){
//        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
//        try {
//            estadoRepository.remover(estadoId);
//
//        } catch(EmptyResultDataAccessException e){
//            throw new EntidadeNaoEncontradaException( String.format("O id [ %d ] da Estado informado não existe", estadoId));
//
//        } catch(DataIntegrityViolationException e){
//            throw new EntidadeEmUsoException( String.format("Estado de código %d não pode ser removida, pois está associado a outro item do banco", estadoId));
//        }
//    }
//
//}
