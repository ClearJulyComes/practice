package com.example.practice.service;

import com.example.practice.daointerface.CustomRepository;
import com.example.practice.view.organizationview.*;
import com.example.practice.model.Organization;
import com.example.practice.model.mapper.MapperFacade;
import com.example.practice.serviceinterface.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final CustomRepository<OrganizationListFilterDto, Organization> organizationRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(CustomRepository<OrganizationListFilterDto, Organization> organizationRepository, MapperFacade mapperFacade) {
        this.organizationRepository = organizationRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OrganizationListView> getAllActive(OrganizationListFilterDto dto) {
        List<Organization> organizations = organizationRepository.findList(dto);
        if (organizations.isEmpty()){
            throw new NoSuchElementException("No organization entities");
        }
        return mapperFacade.mapAsList(organizations, OrganizationListView.class);
    }

    @Override
    public OrganizationIdView getOrganization(int id) {
        Organization organization = organizationRepository.findById(id);
        return mapperFacade.map(organization, OrganizationIdView.class);
    }

    @Override
    public void update(OrganizationUpdateDto dto) {
        Organization organization = organizationRepository.findById(dto.getId());
        mapperFacade.map(dto, organization);
    }

    @Override
    public void save(OrganizationSaveDto dto) {
        Organization organization = mapperFacade.map(dto, Organization.class);
        organizationRepository.save(organization);
    }


}
