package com.algafood.algafoodapiaplication.infrastructure.repository.EM;


import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.algafood.algafoodapiaplication.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Component
//public class RestauranteRepositorylmpl implements RestauranteRepository {
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Override
//    public List<Restaurante> listar() {
//        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
//    }
//
//    @Override
//    public Restaurante buscar(Long id) {
//        return manager.find(Restaurante.class, id);
//    }
//
//    @Transactional
//    @Override
//    public Restaurante adicionarOuAtualizar(Restaurante restaurante) {
//        return manager.merge(restaurante);
//    }
//
//    @Transactional
//    @Override
//    public void remover(Long restauranteId) {
//        Restaurante restaurante = buscar(restauranteId);
//
//        if(restaurante == null){
//            throw new EmptyResultDataAccessException(1);
//        }
//        manager.remove(restaurante);
//    }
//}
