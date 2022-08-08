package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Brasileira");
        restaurante.setTaxaFrete(new BigDecimal("10"));

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Japonesa");
        restaurante2.setTaxaFrete(new BigDecimal("20"));

        restaurante = restauranteRepository.salvar(restaurante);
        restaurante2 = restauranteRepository.salvar(restaurante2);

        System.out.printf("%d - %s\n", restaurante.getId(), restaurante.getNome());
        System.out.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());
    }
}
