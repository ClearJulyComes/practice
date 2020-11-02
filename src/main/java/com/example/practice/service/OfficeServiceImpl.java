package com.example.practice.service;

import com.example.practice.dao.OfficeRepository;
import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import com.example.practice.serviceinterface.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Office> getAllActive() {
        return officeRepository.findByIsActive(true)
                .orElseThrow(()-> new NoSuchElementException("No office entities"));
    }

    @Override
    public Office getOffice(int id) {
        return officeRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No office entity by 'id' "+ id));
    }

    @Override
    public void update(Office officeChanges) {
        Office office = getOffice(officeChanges.getId());
        updateFields(office, officeChanges);
        officeRepository.save(office);
    }

    @Override
    public void save(Office office) {
        officeRepository.save(office);
    }

    private void updateFields(Office office, Office officeChanges){
        office.setName(officeChanges.getName());
        office.setAddress(officeChanges.getAddress());
        office.setActive(officeChanges.isActive());
        if (officeChanges.getPhone() != null){
            office.setPhone(officeChanges.getPhone());
        }
    }
}
