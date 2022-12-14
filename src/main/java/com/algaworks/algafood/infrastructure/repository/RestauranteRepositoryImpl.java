package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> restauranteCriteriaQuery = builder.createQuery(Restaurante.class);

        Root<Restaurante> restauranteRoot = restauranteCriteriaQuery.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasLength(nome)){
            predicates.add(builder.like(restauranteRoot.get("nome"), "%" + nome + "%"));
        }
        if(taxaFreteInicial != null){
            predicates.add(builder.greaterThanOrEqualTo(restauranteRoot.get("taxaFrete"), taxaFreteInicial));
        }

        if(taxaFreteInicial != null){
            predicates.add(builder.lessThanOrEqualTo(restauranteRoot.get("taxaFrete"), taxaFreteFinal));
        }

        restauranteCriteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(restauranteCriteriaQuery)
                .getResultList();
    }
}
