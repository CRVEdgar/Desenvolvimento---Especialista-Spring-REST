package com.algafood.algafoodapiaplication.jpa;

import com.algafood.algafoodapiaplication.AlgaFoodApiAplicationApplication;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

//public class BuscaCozinhaIDMain {
//
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiAplicationApplication.class).web(WebApplicationType.NONE).run(args);
//
//        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
//
//        Cozinha cozinha = cozinhaRepository.buscar(1L);
//
//        System.out.println(cozinha.getNome());
//    }
//
//}
