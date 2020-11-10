package com.example.practice.dao;

import com.example.practice.daointerface.CountryRepository;
import com.example.practice.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for country's entity
 */
@Repository
public class CountryRepositoryImpl implements CountryRepository {

    private final EntityManager entityManager;

    /**
     * Constructor with dependency of {@link EntityManager}
     *
     * @param entityManager injected bean
     */
    @Autowired
    public CountryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all countries
     *
     * @return list of countries
     */
    @Override
    public List<Country> getList() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country  c", Country.class);
        return query.getResultList();
    }

    /**
     * Find country by code
     *
     * @param code individual code of country
     * @return country
     */
    @Override
    public Country findByCode(int code) {
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country  c WHERE c.code = :code", Country.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
