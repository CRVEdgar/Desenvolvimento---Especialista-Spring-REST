package com.algafood.algafoodapiaplication.controller;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.model.Restaurante;
import com.algafood.algafoodapiaplication.domain.repository.RestauranteRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> adicionar (@RequestBody Restaurante restaurante){

        try{
            restaurante = cadastroRestaurante.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
//        return cadastroRestaurante.salvar(restaurante);
    }



    @PutMapping("/{restauranteId}")
    public Restaurante atualizar( @PathVariable Long restauranteId, @RequestBody Restaurante restaurante){

            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "endereco", "dataCadastro", "produtos"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id]/[formaPagamento] INDICA O QUE DEVE SER IGNORADO NA CÓPIA, se nao fizer isso, e o parametro for passado sem nada, o hibernate irá apagar e inserir null no campo informado

            return cadastroRestaurante.salvar(restauranteAtual);

    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {

        cadastroRestaurante.excluir(restauranteId);
        // obs: no controlador deve-se retornar o status do erro,e no serviço deve-se retornar as exceções capturadas
//        try{
//            cadastroRestaurante.excluir(restauranteId);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeNaoEncontradaException e){ // caso o id informado não exista no banco
//            return ResponseEntity.notFound().build();
//
//        } catch(EntidadeEmUsoException e){ //caso o cliente solicite excluir um objeto que tenha associação (forem key) associado a ele, o servidor captura a exceção e retorna o status 409 [conflict]
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }

    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizaParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
    }

    //explicação VD 4.34
    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino){
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        System.out.println(restauranteOrigem);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) ->{
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true); //permite acesso à variavel nome (que é privada)

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            System.out.println(nomePropriedade + " = " + valorPropriedade + " novo valor:" + novoValor);

           ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
