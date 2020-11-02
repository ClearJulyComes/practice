package com.example.practice.serviceinterface;

import com.example.practice.model.Office;

import java.util.List;

public interface OfficeService {
    List<Office> getAllActive();
    Office getOffice(int id);
    void update(Office office);
    void save(Office office);
}
