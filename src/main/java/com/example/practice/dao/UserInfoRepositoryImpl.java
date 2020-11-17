package com.example.practice.dao;

import com.example.practice.daointerface.CustomRepository;
import com.example.practice.view.userview.UserListFilterDto;
import com.example.practice.model.UserInfo;
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
 * Repository for UserInfo's entity DB management
 */
@Repository
public class UserInfoRepositoryImpl implements CustomRepository<UserListFilterDto, UserInfo> {

    private final EntityManager entityManager;

    @Autowired
    public UserInfoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find list of UserInfo by filter using Criteria API
     *
     * @param dto filter
     * @return optional list of UserInfo
     */
    @Override
    public List<UserInfo> findList(UserListFilterDto dto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserInfo> criteriaQuery = criteriaBuilder.createQuery(UserInfo.class);
        Root<UserInfo> root = criteriaQuery.from(UserInfo.class);
        Predicate predicate1
                = criteriaBuilder.equal(root.get("office").get("id"), dto.getOfficeId());
        Predicate finalPredicate = criteriaBuilder.and(predicate1);
        if (dto.getFirstName() != null) {
            Predicate predicate2
                    = criteriaBuilder.equal(root.get("firstName"), dto.getFirstName());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate2);
        }
        if (dto.getSecondName() != null) {
            Predicate predicate3
                    = criteriaBuilder.equal(root.get("secondName"), dto.getSecondName());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate3);
        }
        if (dto.getMiddleName() != null) {
            Predicate predicate4
                    = criteriaBuilder.equal(root.get("middleName"), dto.getMiddleName());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate4);
        }
        if (dto.getPosition() != null) {
            Predicate predicate5
                    = criteriaBuilder.equal(root.get("position"), dto.getPosition());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate5);
        }
        if (dto.getDocCode() != null) {
            Predicate predicate6
                    = criteriaBuilder.equal(root.get("userDoc").get("doc").get("code"), dto.getDocCode());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate6);
        }
        if (dto.getCitizenshipCode() != null) {
            Predicate predicate7
                    = criteriaBuilder.equal(root.get("country").get("code"), dto.getCitizenshipCode());
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate7);
        }
        criteriaQuery.select(root).where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Save UserInfo entity
     *
     * @param dto new user's entity
     */
    @Override
    public void save(UserInfo dto) {
        entityManager.persist(dto);
    }

    /**
     * Find user by id using named query
     *
     * @param id user id
     * @return optional user entity
     */
    @Override
    public UserInfo findById(int id) {
        TypedQuery<UserInfo> query = entityManager.createNamedQuery("findUserById", UserInfo.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
