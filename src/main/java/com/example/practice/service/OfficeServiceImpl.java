package com.example.practice.service;

import com.example.practice.daointerface.OfficeRepository;
import com.example.practice.daointerface.OrganizationRepository;
import com.example.practice.view.officeview.*;
import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import com.example.practice.model.mapper.CustomMapperFacade;
import com.example.practice.serviceinterface.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    @Qualifier("officeMapperFacadeImpl")
    private CustomMapperFacade mapperFacade;

    @Override
    public List<OfficeListView> getAllActive(OfficeListFilterDto dto) {
        List<Office> offices = officeRepository.findList(dto)
                .orElseThrow(()-> new NoSuchElementException("No office entities"));
        return mapperFacade.mapAsList(offices, OfficeListView.class);
    }

    @Override
    public OfficeIdView getOffice(int id) {
        Office office = officeRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No office entity by 'id' "+ id));
        return mapperFacade.map(office, OfficeIdView.class);
    }

    @Override
    public void update(OfficeUpdateDto dto) {
        Office office = officeRepository.findById(dto.getId())
                .orElseThrow(()-> new NoSuchElementException("No office entity by 'id' "+ dto.getId()));
        mapperFacade.map(dto, office);
        officeRepository.save(office);
    }

    @Override
    public void save(OfficeSaveDto dto) {
        Office office = mapperFacade.map(dto, Office.class);
        Organization organization = organizationRepository.findById(dto.getOrgId())
                .orElseThrow(()-> new NoSuchElementException("No organization entity by 'id' "+ dto.getOrgId()));
        office.setOrgId(organization);
        officeRepository.save(office);
    }
}
