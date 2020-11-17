package com.example.practice.service;

import com.example.practice.daointerface.CustomRepository;
import com.example.practice.view.officeview.*;
import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import com.example.practice.model.mapper.MapperFacade;
import com.example.practice.serviceinterface.OfficeService;
import com.example.practice.view.organizationview.OrganizationListFilterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

    private final CustomRepository<OrganizationListFilterDto, Organization> organizationRepository;
    private final CustomRepository<OfficeListFilterDto, Office> officeRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(CustomRepository<OrganizationListFilterDto, Organization> organizationRepository,
                             CustomRepository<OfficeListFilterDto, Office> officeRepository, MapperFacade mapperFacade) {
        this.organizationRepository = organizationRepository;
        this.officeRepository = officeRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OfficeListView> getAllActive(OfficeListFilterDto dto) {
        List<Office> offices = officeRepository.findList(dto);
        if (offices.isEmpty()){
            throw new NoSuchElementException("No office entities");
        }
        return mapperFacade.mapAsList(offices, OfficeListView.class);
    }

    @Override
    public OfficeIdView getOffice(int id) {
        Office office = officeRepository.findById(id);
        return mapperFacade.map(office, OfficeIdView.class);
    }

    @Override
    public void update(OfficeUpdateDto dto) {
        Office office = officeRepository.findById(dto.getId());
        mapperFacade.map(dto, office);
    }

    @Override
    public void save(OfficeSaveDto dto) {
        Office office = mapperFacade.map(dto, Office.class);
        Organization organization = organizationRepository.findById(dto.getOrgId());
        office.setOrganization(organization);
        officeRepository.save(office);
    }
}
