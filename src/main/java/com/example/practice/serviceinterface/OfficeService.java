package com.example.practice.serviceinterface;

import com.example.practice.view.officeview.*;

import java.util.List;

public interface OfficeService {
    List<OfficeListView> getAllActive(OfficeListFilterDto dto);
    OfficeIdView getOffice(int id);
    void update(OfficeUpdateDto dto);
    void save(OfficeSaveDto dto);
}
