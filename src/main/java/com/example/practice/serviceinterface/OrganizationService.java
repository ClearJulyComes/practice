package com.example.practice.serviceinterface;

import com.example.practice.view.organizationview.*;

import java.util.List;

public interface OrganizationService {
    List<OrganizationListView> getAllActive(OrganizationListFilterDto dto);

    OrganizationIdView getOrganization(int id);

    void update(OrganizationUpdateDto organization);

    void save(OrganizationSaveDto organization);
}
