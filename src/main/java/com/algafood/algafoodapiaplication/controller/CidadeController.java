package com.algafood.algafoodapiaplication.controller;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Cidade;
import com.algafood.algafoodapiaplication.domain.repository.CidadeRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;


    @GetMapping
    public List<Cidade> listar(){

        return cidadeRepository.findAll();
    }


    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
       Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

        if(cidade.isPresent() ){
            return ResponseEntity.ok(cidade.get());
        }else{
            System.out.println("ID INVALIDO - ID DA CIDADE INDICADA NÃO ENCONTRADA");

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar (@RequestBody Cidade cidade){

        try{
            cidade = cadastroCidade.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }



    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar( @PathVariable Long cidadeId, @RequestBody Cidade cidade){
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

        if(cidadeAtual.isPresent()){
            BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");

            Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
            return ResponseEntity.ok(cidadeSalva);
        }
        return ResponseEntity.notFound().build();
//        try{
//            Cidade cidadeAtual =  cidadeRepository.buscar(cidadeId);
//
//            if(cidadeAtual != null){
//                BeanUtils.copyProperties(cidade, cidadeAtual, "id"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id] INDICA O QUE DEVE SER IGNORADO NA CÓPIA
//
//                cadastroCidade.salvar(cidadeAtual);
//                return ResponseEntity.ok(cidadeAtual);
//            }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//        }catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }

    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {

        // obs: no controlador deve-se retornar o status do erro,e no serviço deve-se retornar as exceções capturadas
        try{
            cadastroCidade.excluir(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e){ // caso o id informado não exista no banco
            return ResponseEntity.notFound().build();

        } catch(EntidadeEmUsoException e){ //caso o cliente solicite excluir um objeto que tenha associação (foren key) associado a ele, o servidor captura a exceção e retorna o status 409 [conflict]
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }


}
