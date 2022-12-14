package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
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
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar(){
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){

        if(restauranteRepository.findById(restauranteId).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restauranteRepository.findById(restauranteId).get());
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
           Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

           if (restauranteAtual.isPresent()){
               BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id",
                       "formasPagamento",
                       "endereco",
                       "dataCadastro",
                       "produtos");

               Restaurante restauranteSalvo = cadastroRestauranteService.salvar(restauranteAtual.get());
               return ResponseEntity.ok(restauranteSalvo);
           }
           return ResponseEntity.notFound().build();
       }catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> remover(@PathVariable Long restauranteId){
        try {
            cadastroRestauranteService.remover(restauranteId);

            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> dadosOrigem){
       Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

       if(restauranteAtual.isEmpty()){
            return ResponseEntity.notFound().build();
       }

       merge(dadosOrigem, restauranteAtual.get());

       ResponseEntity<?> responseEntity = atualizar(restauranteId, restauranteAtual.get());

       return responseEntity;
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((tipo, valor) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, tipo);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
       });
    }
}
