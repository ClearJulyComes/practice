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
import java.util.NoSuchElementException;

/**
 * Repository for working with office entity
 */
@Repository
public class OfficeRepositoryImpl implements CustomRepository<OfficeListFilterDto, Office> {

    private final EntityManager entityManager;

    @Autowired
    public OfficeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find list of offices from DB by criteria query
     *
     * @param dto filter
     * @return optional list of offices
     */
    @Override
    public List<Office> findList(OfficeListFilterDto dto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);
        Predicate predicate1
                = criteriaBuilder.equal(root.get("organization").get("id"), dto.getOrgId());
        Predicate finalPredicate = criteriaBuilder.and(predicate1);
        if (dto.getName() != null) {
            Predicate predicate2
                    = criteriaBuilder.equal(root.get("name"), dto.getName());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate2);
        }
        if (dto.getPhone() != null) {
            Predicate predicate3
                    = criteriaBuilder.equal(root.get("phone"), dto.getPhone());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate3);
        }
        if (dto.getIsActive() != null) {
            Predicate predicate4
                    = criteriaBuilder.equal(root.get("isActive"), dto.getIsActive());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate4);
        }
        criteriaQuery.select(root).where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
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
    public Office findById(int id) {
        Office office = entityManager.find(Office.class, id);
        if(office==null){
            throw new NoSuchElementException("Нет элементов удовлетворяющих поиску");
        }
        return office;
    }
}
