package com.algafood.algafoodapiaplication.infrastructure.repository;

import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.algafood.algafoodapiaplication.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> consulta(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){

        //var jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";

        /*
        * consulta com JPQL e condição IF
        * */

//        var jpql = new StringBuilder();
//        var parametros = new HashMap<String, Object>();
//
//        jpql.append("from Restaurante where 0=0 ");
//
//        if (StringUtils.hasLength(nome)){
//            jpql.append("and nome like :nome ");
//            parametros.put("nome", "%" + nome +"%");
//        }
//
//        if(taxaFreteInicial != null){
//            jpql.append("and taxaFrete >= :taxaInicial ");
//            parametros.put("taxaInicial", taxaFreteInicial);
//        }
//
//        if (taxaFreteFinal != null){
//            jpql.append("and taxaFrete <= :taxaFinal");
//            parametros.put("taxaFinal", taxaFreteFinal);
//        }
//
//        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//            parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//
//            return query.getResultList();
//                .setParameter("nome", "%" + nome +"%")
//                .setParameter("taxaInicial", taxaFreteInicial)
//                .setParameter("taxaFinal", taxaFreteFinal).getResultList();

        /*
        * CONSULTA UTILIZANDO CRITERIA
        * */

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class); //from Restaurante

        var predicates = new ArrayList<>(); //cria a lista de Predicates

        //verifica se foi passado alfo na referencia
        if(StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%")); //cria o critério
        }
        if(taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if(taxaFreteFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0])); //especifica a condição // o toArray serve para converter a lista em array

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
    }


}
