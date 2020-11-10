package com.example.practice.dao;

import com.example.practice.daointerface.CustomRepository;
import com.example.practice.view.officeview.OfficeListFilterDto;
import com.example.practice.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * Repository for working with office entity
 */
@Repository
public class OfficeRepositoryImpl implements CustomRepository<OfficeListFilterDto, Office> {

    @Autowired
    private EntityManager entityManager;

    /**
     * Find list of offices from DB by criteria query
     *
     * @param dto filter
     * @return optional list of offices
     */
    @Override
    public Optional<List<Office>> findList(OfficeListFilterDto dto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);
        Predicate predicate1
                = criteriaBuilder.equal(root.get("orgId").get("id"), dto.getOrgId());
        Predicate predicate2
                = criteriaBuilder.equal(root.get("name"), dto.getName());
        Predicate predicate3
                = criteriaBuilder.equal(root.get("phone"), dto.getPhone());
        Predicate predicate4
                = criteriaBuilder.equal(root.get("isActive"), dto.getIsActive());
        Predicate finalPredicate = criteriaBuilder.and(predicate1);
        if (dto.getName() != null) {
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate2);
        }
        if (dto.getPhone() != null) {
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate3);
        }
        if (dto.getIsActive() != null) {
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate4);
        }
        criteriaQuery.select(root).where(finalPredicate);
        return Optional.of(entityManager.createQuery(criteriaQuery).getResultList());
    }

    /**
     * Save office entity to the DB
     *
     * @param office entity for saving
     */
    @Override
    public void save(Office office) {
        entityManager.persist(office);
    }

    /**
     * Find office by entity manager using find()
     *
     * @param id id of
     * @return optional office entity
     */
    @Override
    public Optional<Office> findById(int id) {
        return Optional.of(entityManager.find(Office.class, id));
    }
}
