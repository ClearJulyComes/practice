package com.example.practice.service;

import com.example.practice.daointerface.OrganizationRepository;
import com.example.practice.view.organizationview.*;
import com.example.practice.model.Organization;
import com.example.practice.model.mapper.CustomMapperFacade;
import com.example.practice.serviceinterface.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    @Qualifier("organizationMapperFacadeImpl")
    private CustomMapperFacade mapperFacade;

    @Override
    public List<OrganizationListView> getAllActive(OrganizationListFilterDto dto) {
        List<Organization> organizations = organizationRepository.findList(dto)
                .orElseThrow(()-> new NoSuchElementException("No organization entities"));
        return mapperFacade.mapAsList(organizations, OrganizationListView.class);
    }

    @Override
    public OrganizationIdView getOrganization(int id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No organization entity by 'id'  " + id));
        return mapperFacade.map(organization, OrganizationIdView.class);
    }

    @Override
    public void update(OrganizationUpdateDto dto) {
        Organization organization = organizationRepository.findById(dto.getId())
                .orElseThrow(()-> new NoSuchElementException("No organization entity by 'id'  " + dto.getId()));
        mapperFacade.map(dto, organization);
        organizationRepository.save(organization);
    }

    @Override
    public void save(OrganizationSaveDto dto) {
        Organization organization = mapperFacade.map(dto, Organization.class);
        organizationRepository.save(organization);
    }


}
