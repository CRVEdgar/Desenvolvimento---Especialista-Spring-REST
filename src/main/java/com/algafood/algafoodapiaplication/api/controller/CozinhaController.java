package com.algafood.algafoodapiaplication.api.controller;

/*
 * IMPLEMENTAÇÃO DO CONTROLLER COM A INTERFACE JPAREPOSITORY
 * */

import com.algafood.algafoodapiaplication.api.assemblerConvert.CozinhaConvertAssembler;
import com.algafood.algafoodapiaplication.api.assemblerConvert.CozinhaConvertDISAssembler;
import com.algafood.algafoodapiaplication.api.model.CozinhaDTO;
import com.algafood.algafoodapiaplication.api.model.input.CozinhaInput;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Cozinha;
import com.algafood.algafoodapiaplication.domain.repository.CozinhaRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaConvertAssembler cozinhaConvertAssembler;

    @Autowired
    private CozinhaConvertDISAssembler cozinhaConvertDISAssembler;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) //Especifica qual o tipo de resposta suportada
    public List<CozinhaDTO> listar(){
        return cozinhaConvertAssembler.toCollectionDTO(cozinhaRepository.findAll());
//        return cozinhaRepository.findAll();

    }


    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        return cozinhaConvertAssembler.toDTO(cozinha);

//        return cadastroCozinha.buscarOuFalhar(cozinhaId);
//        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//
//        if(cozinha.isPresent() ){
//            return ResponseEntity.ok(cozinha.get());
//        }else{
//            System.out.println("ID INVALIDO - ID DA COZINHA INDICADA NÃO ENCONTRADA");
//
//            return ResponseEntity.notFound().build();
//        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar (@RequestBody @Valid CozinhaInput cozinhaInput){
        Cozinha cozinha = cozinhaConvertDISAssembler.toDomainObject(cozinhaInput);

        return cozinhaConvertAssembler.toDTO(cadastroCozinha.salvar(cozinha));

//        return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar( @PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput){

        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

        cozinhaConvertDISAssembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        return cozinhaConvertAssembler.toDTO(cadastroCozinha.salvar(cozinhaAtual));

//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id] INDICA O QUE DEVE SER IGNORADO NA CÓPIA
//
//        return cadastroCozinha.salvar(cozinhaAtual);

    }

//    @DeleteMapping("/{cozinhaId}")
//    public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
//
//        // obs: no controlador deve-se retornar o status do erro,e no serviço deve-se retornar as exceções capturadas
//        try{
//            cadastroCozinha.excluir(cozinhaId);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeNaoEncontradaException e){ // caso o id informado não exista no banco
//            return ResponseEntity.notFound().build();
//
//        } catch(EntidadeEmUsoException e){ //caso o cliente solicite excluir um objeto que tenha associação (forem key) associado a ele, o servidor captura a exceção e retorna o status 409 [conflict]
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }

        @DeleteMapping("/{cozinhaId}")
        @ResponseStatus(HttpStatus.NO_CONTENT) //em caso de sucesso retorna o status
        public void remover(@PathVariable Long cozinhaId) {
            cadastroCozinha.excluir(cozinhaId);

        }


}
