package com.algafood.algafoodapiaplication.controller;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
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
    }


    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
       Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if(restaurante.isPresent() ){
            return ResponseEntity.ok(restaurante.get());
        }else{
            System.out.println("ID INVALIDO - ID DO RESTAURANTE INDICADO NÃO ENCONTRADO");
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<?> atualizar( @PathVariable Long restauranteId, @RequestBody Restaurante restaurante){

        try{
            Optional<Restaurante> restauranteAtual =  restauranteRepository.findById(restauranteId);

            if(restauranteAtual.isPresent()){
                BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formaPagamento", "endereco", "dataCadastro"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id]/[formaPagamento] INDICA O QUE DEVE SER IGNORADO NA CÓPIA, se nao fizer isso, e o parametro for passado sem nada, o hibernate irá apagar e inserir null no campo informado

               Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual.get());
                return ResponseEntity.ok(restauranteSalvo);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> remover(@PathVariable Long restauranteId) {

        // obs: no controlador deve-se retornar o status do erro,e no serviço deve-se retornar as exceções capturadas
        try{
            cadastroRestaurante.excluir(restauranteId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e){ // caso o id informado não exista no banco
            return ResponseEntity.notFound().build();

        } catch(EntidadeEmUsoException e){ //caso o cliente solicite excluir um objeto que tenha associação (forem key) associado a ele, o servidor captura a exceção e retorna o status 409 [conflict]
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizaParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if(restauranteAtual.isPresent()){
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual.get());

        return atualizar(restauranteId, restauranteAtual.get());
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
