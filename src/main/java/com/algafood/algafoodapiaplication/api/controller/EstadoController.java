package com.algafood.algafoodapiaplication.api.controller;

import com.algafood.algafoodapiaplication.api.assemblerConvert.EstadoConvertAssembler;
import com.algafood.algafoodapiaplication.api.assemblerConvert.EstadoConvertDISAssembler;
import com.algafood.algafoodapiaplication.api.model.EstadoDTO;
import com.algafood.algafoodapiaplication.api.model.input.EstadoInput;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapiaplication.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapiaplication.domain.model.Estado;
import com.algafood.algafoodapiaplication.domain.repository.EstadoRepository;
import com.algafood.algafoodapiaplication.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController // inclui @componente e @responseBody
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoConvertAssembler estadoConvertAssembler;

    @Autowired
    private EstadoConvertDISAssembler estadoConvertDISAssembler;

    @GetMapping
    public List<EstadoDTO> listar(){
        return estadoConvertAssembler.toCollectionDTO(estadoRepository.findAll());
//        return estadoRepository.findAll();
    }


    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId){
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        return estadoConvertAssembler.toDTO(estado);
//        return cadastroEstado.buscarOuFalhar(estadoId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO adicionar (@RequestBody @Valid EstadoInput estadoInput){
        Estado estado = estadoConvertDISAssembler.toDomainObject(estadoInput);
        return estadoConvertAssembler.toDTO(cadastroEstado.salvar(estado));
//        return cadastroEstado.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar( @PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){

        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

        estadoConvertDISAssembler.copyToDomainObject(estadoInput, estadoAtual);

        return estadoConvertAssembler.toDTO(cadastroEstado.salvar(estadoAtual));

//        BeanUtils.copyProperties(estado, estadoAtual, "id");
//
//        return cadastroEstado.salvar(estadoAtual);

    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {

        cadastroEstado.excluir(estadoId);

    }

}
