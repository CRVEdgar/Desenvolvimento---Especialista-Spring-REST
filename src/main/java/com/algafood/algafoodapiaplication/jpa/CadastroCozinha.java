//package com.algafood.algafoodapiaplication.jpa;
//
//import com.algafood.algafoodapiaplication.domain.model.Cozinha;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Component
//public class CadastroCozinha {
//
//    @PersistenceContext
//     private EntityManager manager;
//
//    public List<Cozinha> listar(){
//        return manager.createQuery("from Cozinha", Cozinha.class).getResultList(); //linguahuem JPQL faz consulta em OBJETOS e nao em tabelas
////        return manager.createQuery("from tab_cozinhas", Cozinha.class).getResultList(); //aqui está errado porque retorna uma tabela
//    }
//
//    @Transactional
//    public Cozinha adicionarOuAtualizar(Cozinha cozinha){
//        return manager.merge(cozinha); //return serve para atribuir o id do objeto retornado pelo metodo merge
//    }
//
//    public Cozinha buscar(Long id){
//        return manager.find(Cozinha.class, id);
////        return manager.findById(id); //somente quando há uma class implements o JPA Repository
//    }
//
//    @Transactional
//    public void remover (Cozinha cozinha){
//        cozinha = buscar(cozinha.getId());
//        manager.remove(cozinha);
//    }
//}
