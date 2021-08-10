package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

     List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal); //filtra por um intervalo de taxa

     List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long id); //filtra pelo nome e id da cozinha

     Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome); //findFirst - filtra pelo primeira ocorrencia

     List<Restaurante> findTop2ByNomeContaining(String nome); // findTop + [QTD] - filtra pela QTD dos primeiros registros encontrados

     int countByCozinhaId (Long cozinhaId); //conta a quantidade de restaurantes cadastrados na cozinha informada

     /*
     * CONSULTAS COM JPQL
     * */

     @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
     List<Restaurante> consultarPorNomeId(String nome, @Param("id") Long cozinha); //rlz a consulta pr nome e id da cozinha

     /*
     * CONSULTAS COM ENTITY MANAGER
     * */
//    List<Restaurante> listar();
//    Restaurante buscar (Long id);
//    Restaurante adicionarOuAtualizar(Restaurante restaurante);
//    void remover(Long id);

}
