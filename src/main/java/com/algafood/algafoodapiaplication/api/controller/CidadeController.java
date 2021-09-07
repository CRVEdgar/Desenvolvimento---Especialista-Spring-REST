package com.algafood.algafoodapiaplication.api.controller;

import com.algafood.algafoodapiaplication.api.assemblerConvert.CidadeConvertAssembler;
import com.algafood.algafoodapiaplication.api.assemblerConvert.CidadeConvertDISAssembler;
import com.algafood.algafoodapiaplication.api.model.CidadeDTO;
import com.algafood.algafoodapiaplication.api.model.input.CidadeInput;
import com.algafood.algafoodapiaplication.domain.exception.EstadoNaoEncontradoException;
import com.algafood.algafoodapiaplication.domain.exception.NegocioException;
import com.algafood.algafoodapiaplication.domain.model.Cidade;
import com.algafood.algafoodapiaplication.domain.repository.CidadeRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeConvertAssembler cidadeConvertAssembler;

    @Autowired
    private CidadeConvertDISAssembler cidadeConvertDISAssembler;


    @GetMapping
    public List<CidadeDTO> listar(){
        return cidadeConvertAssembler.toCollectionDTO(cidadeRepository.findAll());

//        return cidadeRepository.findAll();
    }


    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId){
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeConvertAssembler.toDTO(cidade);
//        return cadastroCidade.buscarOuFalhar(cidadeId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar (@RequestBody @Valid CidadeInput cidadeInput){
        try{
            Cidade cidade = cidadeConvertDISAssembler.toDomainObject(cidadeInput);

            return cidadeConvertAssembler.toDTO(cadastroCidade.salvar(cidade));
//            return cadastroCidade.salvar(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
        
    }


    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar( @PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput){
        try{
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeConvertDISAssembler.copyToDomainObject(cidadeInput, cidadeAtual);

            return cidadeConvertAssembler.toDTO(cadastroCidade.salvar(cidadeAtual));
//
//            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
//
//            return cadastroCidade.salvar(cidadeAtual);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e); // a captura dessa exception é para garantir que seja retornado o codigo 400 quando noa for encntrado o estado informado
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);

    }

//    //metodo para customizar a reposta em caso de exception
//    @ExceptionHandler(EntidadeNaoEncontradaException.class)
//    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
//            EntidadeNaoEncontradaException e){
//
//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem(e.getMessage())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
//    }
//
//    @ExceptionHandler(NegocioException.class)
//    public ResponseEntity<?> tratarNegocioException(
//            NegocioException e){
//
//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem(e.getMessage())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
//    }

}
