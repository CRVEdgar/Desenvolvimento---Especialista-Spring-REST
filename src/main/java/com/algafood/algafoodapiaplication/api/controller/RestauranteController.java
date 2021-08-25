package com.algafood.algafoodapiaplication.api.controller;

import com.algafood.algafoodapiaplication.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.NegocioException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.algafood.algafoodapiaplication.domain.repository.RestauranteRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;


    @GetMapping
    public List<Restaurante> listar(){

        return restauranteRepository.findAll();
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        System.out.println("\n O NOME DA COZINHA É?");
//        System.out.println(restaurantes.get(0).getCozinha().getNome());
//        return restaurantes;
    }


    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId){
        return cadastroRestaurante.buscarOuFalhar(restauranteId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar (@RequestBody Restaurante restaurante){

        try {
            return cadastroRestaurante.salvar(restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @PutMapping("/{restauranteId}")
    public Restaurante atualizar( @PathVariable Long restauranteId, @RequestBody Restaurante restaurante){

        try{
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "endereco", "dataCadastro", "produtos"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id]/[formaPagamento] INDICA O QUE DEVE SER IGNORADO NA CÓPIA, se nao fizer isso, e o parametro for passado sem nada, o hibernate irá apagar e inserir null no campo informado

            return cadastroRestaurante.salvar(restauranteAtual);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {

        cadastroRestaurante.excluir(restauranteId);

    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizaParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request){
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        merge(campos, restauranteAtual, request);

        return atualizar(restauranteId, restauranteAtual);
    }

    //explicação VD 4.34 /8.24
    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request){
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            System.out.println(restauranteOrigem);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true); //permite acesso à variavel nome (que é privada)

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                System.out.println(nomePropriedade + " = " + valorPropriedade + " novo valor:" + novoValor);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        }catch(IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(),rootCause, serverHttpRequest);
        }
    }

}
