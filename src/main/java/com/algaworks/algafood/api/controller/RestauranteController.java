package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar(){
        return ResponseEntity.ok(restauranteRepository.listar());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){

        if(restauranteRepository.buscar(restauranteId) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restauranteRepository.buscar(restauranteId));
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante){
       try {
           return ResponseEntity.status(HttpStatus.CREATED).body(cadastroRestauranteService.salvar(restaurante));
       } catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.badRequest()
                   .body(e.getMessage());
       }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                        @RequestBody Restaurante restaurante){
       try{
           Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

           if (restauranteAtual != null){
               BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

               restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
               return ResponseEntity.ok(restauranteAtual);
           }
           return ResponseEntity.notFound().build();
       }catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Void> remover(@PathVariable Long restauranteId){
        try {
            cadastroRestauranteService.remover(restauranteId);

            return ResponseEntity.noContent().build();


        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
}
