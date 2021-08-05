package com.algafood.algafoodapiaplication.infrastructure.repository;

import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositorylmpl implements CozinhaRepository {

    @PersistenceContext
     private EntityManager manager;

    @Override
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList(); //linguahuem JPQL faz consulta em OBJETOS e nao em tabelas
//        return manager.createQuery("from tab_cozinhas", Cozinha.class).getResultList(); //aqui está errado porque retorna uma tabela
    }

    @Transactional
    @Override
    public Cozinha adicionarOuAtualizar(Cozinha cozinha){
        return manager.merge(cozinha); //return serve para atribuir o id do objeto retornado pelo metodo merge
    }

    @Override
    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class, id);
//        return manager.findById(id); //somente quando há uma class implements o JPA Repository
    }

    @Transactional
    @Override
    public void remover (Long id){
        Cozinha cozinha = buscar(id);

        if(cozinha == null){
            throw new EmptyResultDataAccessException(1); //parametro [1] indica quantos objetos deveriam ser encontrados na busca
        }
        manager.remove(cozinha);
    }
}
