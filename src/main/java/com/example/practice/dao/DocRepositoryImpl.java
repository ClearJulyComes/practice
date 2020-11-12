package com.example.practice.dao;

import com.example.practice.daointerface.DocRepository;
import com.example.practice.model.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for doc's entity
 */
@Repository
public class DocRepositoryImpl implements DocRepository {

    private final EntityManager entityManager;

    @Autowired
    public DocRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all docs from DB
     *
     * @return list of all doc's entities
     */
    @Override
    public List<Doc> getList() {
        TypedQuery<Doc> query = entityManager.createQuery("SELECT d FROM Doc d", Doc.class);
        return query.getResultList();
    }

    /**
     * Find doc by code
     *
     * @param code of doc
     * @return doc entity
     */
    @Override
    public Doc findByCode(int code) {
        TypedQuery<Doc> query = entityManager.createQuery("SELECT d FROM Doc  d WHERE d.code = :code", Doc.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    /**
     * Find doc by name
     *
     * @param name of doc
     * @return doc entity
     */
    @Override
    public Doc findByName(String name) {
        TypedQuery<Doc> query = entityManager.createQuery("SELECT d FROM Doc d WHERE d.name = :name", Doc.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
