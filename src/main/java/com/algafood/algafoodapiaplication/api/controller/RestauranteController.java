package com.algafood.algafoodapiaplication.api.controller;

import com.algafood.algafoodapiaplication.api.assemblerConvert.RestauranteConvertAssembler;
import com.algafood.algafoodapiaplication.api.model.CozinhaDTO;
import com.algafood.algafoodapiaplication.api.model.RestauranteDTO;
import com.algafood.algafoodapiaplication.api.model.input.RestauranteInput;
import com.algafood.algafoodapiaplication.domain.exception.CozinhaNaoEncontradaException;
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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteConvertAssembler restauranteConvertAssembler;


    @GetMapping
    public List<RestauranteDTO> listar(){

        return restauranteConvertAssembler.toCollectionDTO(restauranteRepository.findAll());

    }


    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId){

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteConvertAssembler.toDTO(restaurante); // devolve o objeto no formato DTO
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar (@RequestBody @Valid RestauranteInput restauranteInput){

        try {
            Restaurante restaurante = toDomainObject(restauranteInput); // recebe o objeto no formato Input

            return restauranteConvertAssembler.toDTO(cadastroRestaurante.salvar(restaurante)); // devolve o objeto no formato DTO
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar( @PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){

        try{
            Restaurante restaurante = toDomainObject(restauranteInput); // recebe o objeto no formato Input

            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "endereco", "dataCadastro", "produtos"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id]/[formaPagamento] INDICA O QUE DEVE SER IGNORADO NA CÓPIA, se nao fizer isso, e o parametro for passado sem nada, o hibernate irá apagar e inserir null no campo informado

            return restauranteConvertAssembler.toDTO(cadastroRestaurante.salvar(restauranteAtual));
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {

        cadastroRestaurante.excluir(restauranteId);

    }

//    @PatchMapping("/{restauranteId}")
//    public RestauranteDTO atualizaParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request){
//        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
//        merge(campos, restauranteAtual, request);
//
//        // TO DO: CONVERTER restauranteAtual P/ restauranteInput
//        return atualizar(restauranteId, restauranteAtual);
//    }

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





    // metodo que recebe um Restaurante enviado na requisição e converte para um tipo Restaurante do dominio
    private Restaurante toDomainObject(RestauranteInput restauranteInput){

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }


}
