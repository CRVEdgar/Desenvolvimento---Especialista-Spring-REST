package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {


//    List<Restaurante> listar();
//    Restaurante buscar (Long id);
//    Restaurante adicionarOuAtualizar(Restaurante restaurante);
//    void remover(Long id);

}
