package com.example.practice.dao;

import com.example.practice.model.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    Optional<List<Organization>> findByIsActive(boolean isActive);
}
