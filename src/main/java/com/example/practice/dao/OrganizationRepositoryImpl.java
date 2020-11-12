package com.example.practice.dao;

import com.example.practice.daointerface.CustomRepository;
import com.example.practice.view.organizationview.OrganizationListFilterDto;
import com.example.practice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * Repository for working with organization entity
 */
@Repository
public class OrganizationRepositoryImpl implements CustomRepository<OrganizationListFilterDto, Organization> {
    private final EntityManager entityManager;

    @Autowired
    public OrganizationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find organizations in DB by filter. Using Criteria API for creating query by filter
     *
     * @param dto filter
     * @return optional list of organizations
     */
    @Override
    public List<Organization> findList(OrganizationListFilterDto dto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> root = criteriaQuery.from(Organization.class);
        Predicate predicate1
                = criteriaBuilder.equal(root.get("name"), dto.getName());
        Predicate predicate2
                = criteriaBuilder.equal(root.get("inn"), dto.getInn());
        Predicate predicate3
                = criteriaBuilder.equal(root.get("isActive"), dto.getIsActive());
        Predicate finalPredicate = criteriaBuilder.and(predicate1);
        if (dto.getInn() != null) {
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate2);
        }
        if (dto.getIsActive() != null) {
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate3);
        }
        criteriaQuery.select(root).where(finalPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Save organization to DB
     *
     * @param organization organization entity
     */
    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }

    /**
     * Find organization by id using named query
     *
     * @param id organization id
     * @return optional organization entity
     */
    @Override
    public Optional<Organization> findById(int id) {
        TypedQuery<Organization> query = entityManager.createNamedQuery("findById", Organization.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }
}
