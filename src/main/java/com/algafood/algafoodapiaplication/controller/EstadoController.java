package com.algafood.algafoodapiaplication.controller;

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

import java.util.List;
import java.util.Optional;


@RestController // inclui @componente e @responseBody
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }


    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if(estado.isPresent() ){
            return ResponseEntity.ok(estado.get());
        }else{
            System.out.println("ID INVALIDO - ID DO ESTADO INDICADO NÃO ENCONTRADO");
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar (@RequestBody Estado estado){

        return cadastroEstado.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar( @PathVariable Long estadoId, @RequestBody Estado estado){
        Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);

        if(estadoAtual.isPresent()){
            //cozinhaAtual.setNome(cozinha.getNome());
            BeanUtils.copyProperties(estado, estadoAtual.get(), "id"); // fazendo uma cópia utilizando a classe BeanUtils | O TERCEIRO PARAMETRO [id] INDICA O QUE DEVE SER IGNORADO NA CÓPIA

            Estado estadoSalvo = cadastroEstado.salvar(estadoAtual.get());
            return ResponseEntity.ok(estadoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {

        // obs: no controlador deve-se retornar o status do erro,e no serviço deve-se retornar as exceções capturadas
        try{
            cadastroEstado.excluir(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e){ // caso o id informado não exista no banco
            return ResponseEntity.notFound().build();

        } catch(EntidadeEmUsoException e){ //caso o cliente solicite excluir um objeto que tenha associação (forem key) associado a ele, o servidor captura a exceção e retorna o status 409 [conflict]
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}
