package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha buscar (Long id);
    Cozinha adicionarOuAtualizar(Cozinha cozinha);
    void remover(Long id);

}
