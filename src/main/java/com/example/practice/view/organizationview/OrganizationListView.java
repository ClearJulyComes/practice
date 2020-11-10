package com.example.practice.view.organizationview;

import lombok.Data;

@Data
public class OrganizationListView {
    private int id;
    private String name;
    private Boolean isActive;

    public OrganizationListView(int id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
    public OrganizationListView(){

    }
}
