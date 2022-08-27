package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        TypedQuery<Cozinha> typedQuery = manager.createQuery("from Cozinha", Cozinha.class);
        return typedQuery.getResultList();

        /*
            return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
         */
    }
    @Override
    public List<Cozinha> consultarPorNome(String nome) {
        return manager.createQuery("from Cozinha where name like :name", Cozinha.class)
                .setParameter("name", "%" + nome + "%")
                .getResultList();
    }
    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }


    @Transactional
    @Override
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);

        if(cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cozinha);
    }
}