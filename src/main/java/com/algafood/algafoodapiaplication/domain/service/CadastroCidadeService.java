package com.algafood.algafoodapiaplication.domain.service;

import com.algafood.algafoodapiaplication.domain.exception.CidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Cidade;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.algafood.algafoodapiaplication.domain.repository.CidadeRepository;
import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está associada a outro item do banco";

//    private static final String MSG_CIDADE_NAO_ENCONTRADA
//            = "ID INVALIDO - ID [ %d ] DA CIDADE INDICADA NÃO ENCONTRADA";
//
//    private static final String MSG_ESTADO_NAO_ENCONTRADO
//            = "ID INVALIDO - ID [ %d ] DO ESTADO INDICADO NÃO ENCONTRADO";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

//    @Autowired
//    private EstadoRepository estadoRepository;

    @Transactional
    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
//        Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);

    }

    @Transactional
    public void excluir(Long cidadeId){
        //obs: no serviço deve-se caputra e lançar as exceções capturadas, e no controller deve-se lançar o status do erro assoiado à exceção
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch(EmptyResultDataAccessException e){
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException( String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId){
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}
