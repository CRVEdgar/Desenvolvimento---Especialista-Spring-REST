package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cidade;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;

import java.util.List;

public interface CidadeRepository1 {

    List<Cidade> listar();
    Cidade buscar (Long id);
    Cidade adicionarOuAtualizar(Cidade Cidade);
    void remover(Long id);

}
