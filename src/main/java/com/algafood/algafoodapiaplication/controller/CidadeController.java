package com.algafood.algafoodapiaplication.controller;

import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.exception.NegocioException;
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
    public Cidade buscar(@PathVariable Long cidadeId){

        return cadastroCidade.buscarOuFalhar(cidadeId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar (@RequestBody Cidade cidade){
        try{
            return cadastroCidade.salvar(cidade);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
        
    }


    @PutMapping("/{cidadeId}")
    public Cidade atualizar( @PathVariable Long cidadeId, @RequestBody Cidade cidade){
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try{ // a captura dessa exception é para garantir que seja retornado o codigo 400 quando noa for encntrado o estado informado
            return cadastroCidade.salvar(cidadeAtual);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);

    }


}
