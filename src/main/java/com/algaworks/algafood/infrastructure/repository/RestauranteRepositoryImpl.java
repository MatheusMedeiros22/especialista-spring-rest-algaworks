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

        Predicate nomePredicate = builder.like(restauranteRoot.get("nome"), "%" + nome + "%");
        Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(restauranteRoot.get("taxaFrete"), taxaFreteInicial);
        Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(restauranteRoot.get("taxaFrete"), taxaFreteFinal);

        restauranteCriteriaQuery.where(nomePredicate, taxaFinalPredicate, taxaInicialPredicate);

        return entityManager.createQuery(restauranteCriteriaQuery)
                .getResultList();
    }
}
