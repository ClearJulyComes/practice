package com.example.practice.service;

import com.example.practice.dao.OrganizationRepository;
import com.example.practice.model.Organization;
import com.example.practice.serviceinterface.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAllActive() {
        return organizationRepository.findByIsActive(true)
                .orElseThrow(()-> new NoSuchElementException("No organization entities"));
    }

    @Override
    public Organization getOrganization(int id) {
        return organizationRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No organization entity by 'id'  " + id));
    }

    @Override
    public void update(Organization organizationChanges) {
        Organization organization = getOrganization(organizationChanges.getId());
        updateFields(organization, organizationChanges);
        organizationRepository.save(organization);
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    private void updateFields(Organization organization, Organization organizationChanges){
        organization.setName(organizationChanges.getName());
        organization.setFullName(organizationChanges.getFullName());
        organization.setInn(organizationChanges.getInn());
        organization.setKpp(organizationChanges.getKpp());
        organization.setAddress(organizationChanges.getAddress());
        if (organizationChanges.getPhone() != null){
            organization.setPhone(organizationChanges.getPhone());
        }
    }
}
