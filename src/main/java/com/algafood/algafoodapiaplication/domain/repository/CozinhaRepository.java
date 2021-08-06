package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {



//    List<Cozinha> listar();
//    List<Cozinha> consultarPorNome(String nome);
//    Cozinha buscar (Long id);
//    Cozinha adicionarOuAtualizar(Cozinha cozinha);
//    void remover(Long id);

}
