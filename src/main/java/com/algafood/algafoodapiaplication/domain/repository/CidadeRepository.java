package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

//    List<Cidade> listar();
//    Cidade buscar (Long id);
//    Cidade adicionarOuAtualizar(Cidade Cidade);
//    void remover(Long id);

}
