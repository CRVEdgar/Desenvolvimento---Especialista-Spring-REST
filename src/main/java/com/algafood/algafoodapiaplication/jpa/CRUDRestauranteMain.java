package com.algafood.algafoodapiaplication.jpa;

import com.algafood.algafoodapiaplication.AlgaFoodApiAplicationApplication;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import com.algafood.algafoodapiaplication.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

//public class CRUDRestauranteMain {
//
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiAplicationApplication.class).web(WebApplicationType.NONE).run(args);
//
////        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
//        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
//
////        Cozinha coz1 = new Cozinha(null, "japonesa");
////        Cozinha coz2 = new Cozinha(null, "Asiatica");
////
////        cadastroCozinha.adicionarOuAtualizar(coz1);
////        cadastroCozinha.adicionarOuAtualizar(coz2);
//
////        Restaurante rest1 =new Restaurante();
////        rest1.setNome("Panela de Minas");
////        rest1.setTaxaFrete(new BigDecimal(0.8));
////
////        Restaurante rest2 =new Restaurante();
////        rest2.setId(1L);
////        rest2.setNome("Cabana");
////
////        Restaurante rest3 =new Restaurante();
////        rest3.setId(2L);
//
////        restauranteRepository.adicionarOuAtualizar(rest1);
////        restauranteRepository.adicionarOuAtualizar(rest2);
////        restauranteRepository.remover(rest3);
////        restauranteRepository.buscar(1L);
//
//        List<Restaurante> todosRestaurantes = restauranteRepository.listar();
//
//        for(Restaurante rest: todosRestaurantes){
//            System.out.println("DADOS: " + rest.getNome() + " | " + rest.getTaxaFrete() + " | " + rest.getCozinha().getNome());
//        }
//
//    }
//
//}
