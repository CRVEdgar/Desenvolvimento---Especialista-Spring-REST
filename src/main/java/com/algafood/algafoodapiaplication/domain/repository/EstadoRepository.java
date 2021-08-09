package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

//    List<Estado> listar();
//    Estado buscar (Long id);
//    Estado adicionarOuAtualizar(Estado estado);
//    void remover(Long id);

}
