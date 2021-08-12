package com.algafood.algafoodapiaplication;

import com.algafood.algafoodapiaplication.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgaFoodApiAplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgaFoodApiAplicationApplication.class, args);
    }

}
