package com.algafood.algafoodapiaplication.domain.service;

//import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
//import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
//import com.algafood.algafoodapiaplication.domain.model.Cidade;
//import com.algafood.algafoodapiaplication.domain.model.Estado;
//import com.algafood.algafoodapiaplication.domain.repository.CidadeRepository;
//import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CadastroCidadeService {
//
//    @Autowired
//    private CidadeRepository cidadeRepository;
//
//    @Autowired
//    private EstadoRepository estadoRepository;
//
//    public Cidade salvar(Cidade cidade){
//        Long estadoId = cidade.getEstado().getId();
//        Estado estado = estadoRepository.buscar(estadoId);
//
//        if(estado == null){
//            throw new EntidadeNaoEncontradaException(String.format("Não existe Estado com o código [ %d ] informado", estadoId));
//        }
//
//        cidade.setEstado(estado);
//        return cidadeRepository.adicionarOuAtualizar(cidade);
//
//    }
//
//    public void excluir(Long cidadeId){
//        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
//        try {
//            cidadeRepository.remover(cidadeId);
//
//        } catch(EmptyResultDataAccessException e){
//            throw new EntidadeNaoEncontradaException( String.format("O id [ %d ] do Estado informado não existe", cidadeId));
//
//        } catch(DataIntegrityViolationException e){
//            throw new EntidadeEmUsoException( String.format("Cidade de código %d não pode ser removida, pois está associado a outro item do banco", cidadeId));
//        }
//    }
//}
