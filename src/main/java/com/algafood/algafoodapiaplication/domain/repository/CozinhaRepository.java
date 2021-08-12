package com.algafood.algafoodapiaplication.domain.repository;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    //o nome do metodo tem que ser igual a um nome de variavel da classe | ou usar o findBy como prefixo
    //keywords [containing = like % ]
    List<Cozinha> findTodasByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome); //vrf se existe dados com o barametro informado

//    List<Cozinha> listar();

//    Cozinha buscar (Long id);
//    Cozinha adicionarOuAtualizar(Cozinha cozinha);
//    void remover(Long id);

}
