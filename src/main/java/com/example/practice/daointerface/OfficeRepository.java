package com.example.practice.daointerface;

import com.example.practice.view.officeview.OfficeListFilterDto;
import com.example.practice.model.Office;

import java.util.List;
import java.util.Optional;

/**
 * Office repository interface
 */
public interface OfficeRepository {
    Optional<List<Office>> findList(OfficeListFilterDto dto);
    void save(Office office);
    Optional<Office> findById(int id);
}
