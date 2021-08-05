package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Estado;

import java.util.List;

public interface EstadoRepository1 {

    List<Estado> listar();
    Estado buscar (Long id);
    Estado adicionarOuAtualizar(Estado estado);
    void remover(Long id);

}
