package com.example.practice.dao;

import com.example.practice.daointerface.UserInfoRepository;
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
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Autowired
    private EntityManager entityManager;

    /**
     * Find list of UserInfo by filter using Criteria API
     * @param dto filter
     * @return optional list of UserInfo
     */
    @Override
    public Optional<List<UserInfo>> findList(UserListFilterDto dto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserInfo> criteriaQuery = criteriaBuilder.createQuery(UserInfo.class);
        Root<UserInfo> root = criteriaQuery.from(UserInfo.class);
        Predicate predicate1
                = criteriaBuilder.equal(root.get("officeId").get("id"), dto.getOfficeId());
        Predicate predicate2
                = criteriaBuilder.equal(root.get("firstName"), dto.getFirstName());
        Predicate predicate3
                = criteriaBuilder.equal(root.get("secondName"), dto.getSecondName());
        Predicate predicate4
                = criteriaBuilder.equal(root.get("middleName"), dto.getMiddleName());
        Predicate predicate5
                = criteriaBuilder.equal(root.get("position"), dto.getPosition());
        Predicate predicate6
                = criteriaBuilder.equal(root.get("userDoc").get("docId").get("code"), dto.getDocCode());
        Predicate predicate7
                = criteriaBuilder.equal(root.get("countryId").get("code"), dto.getCitizenshipCode());
        Predicate finalPredicate = criteriaBuilder.and(predicate1);
        if (dto.getFirstName()!= null){
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate2);
        }
        if (dto.getSecondName()!= null){
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate3);
        }
        if (dto.getMiddleName()!= null){
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate4);
        }
        if (dto.getPosition()!= null){
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate5);
        }
        if (dto.getDocCode()!= null){
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate6);
        }
        if (dto.getCitizenshipCode()!= null){
            finalPredicate = criteriaBuilder.and(finalPredicate, predicate7);
        }
        criteriaQuery.select(root).where(finalPredicate);
        return Optional.of(entityManager.createQuery(criteriaQuery).getResultList());
    }

    /**
     * Save UserInfo entity
     * @param dto new user's entity
     */
    @Override
    public void save(UserInfo dto) {
        entityManager.persist(dto);
    }

    /**
     * Find user by id using named query
     * @param id user id
     * @return optional user entity
     */
    @Override
    public Optional<UserInfo> findById(int id) {
        TypedQuery<UserInfo> query = entityManager.createNamedQuery("findUserById", UserInfo.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
    }
}
