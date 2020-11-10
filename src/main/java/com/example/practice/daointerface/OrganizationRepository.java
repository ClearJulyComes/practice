package com.example.practice.daointerface;

import com.example.practice.view.organizationview.OrganizationListFilterDto;
import com.example.practice.model.Organization;

import java.util.List;
import java.util.Optional;

/**
 * Organization repository interface
 */
public interface OrganizationRepository {
    Optional<List<Organization>> findList(OrganizationListFilterDto dto);
    void save(Organization organization);
    Optional<Organization> findById(int id);
}
