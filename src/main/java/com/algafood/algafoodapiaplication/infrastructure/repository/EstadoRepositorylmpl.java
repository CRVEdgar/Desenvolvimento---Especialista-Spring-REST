package com.algafood.algafoodapiaplication.infrastructure.repository;

import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Component
//public class EstadoRepositorylmpl implements EstadoRepository {
//
//    @PersistenceContext
//     private EntityManager manager;
//
//    @Override
//    public List<Estado> listar(){
//        return manager.createQuery("from Estado", Estado.class).getResultList(); //linguahuem JPQL faz consulta em OBJETOS e nao em tabelas
////        return manager.createQuery("from tab_cozinhas", Cozinha.class).getResultList(); //aqui está errado porque retorna uma tabela
//    }
//
//    @Transactional
//    @Override
//    public Estado adicionarOuAtualizar(Estado estado){
//        return manager.merge(estado); //return serve para atribuir o id do objeto retornado pelo metodo merge
//    }
//
//    @Override
//    public Estado buscar(Long id){
//        return manager.find(Estado.class, id);
////        return manager.findById(id); //somente quando há uma class implements o JPA Repository
//    }
//
//    @Transactional
//    @Override
//    public void remover (Long id){
//        Estado estado = buscar(id);
//
//        if(estado == null){
//            throw new EmptyResultDataAccessException(1); //parametro [1] indica quantos objetos deveriam ser encontrados na busca
//        }
//        manager.remove(estado);
//    }
//}
