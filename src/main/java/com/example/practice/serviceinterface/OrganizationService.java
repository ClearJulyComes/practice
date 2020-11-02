package com.example.practice.serviceinterface;

import com.example.practice.model.Organization;

import java.util.List;

public interface OrganizationService {
    List<Organization> getAllActive();
    Organization getOrganization(int id);
    void update(Organization organization);
    void save(Organization organization);
}
