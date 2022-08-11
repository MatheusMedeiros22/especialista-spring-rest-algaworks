package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    //GET /cozinhas HTTP/1.1

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar(){
        List<Cozinha> cozinhas = cozinhaRepository.listar();
        return ResponseEntity.ok(cozinhas);
    }


    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if(cozinha != null){
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }
}