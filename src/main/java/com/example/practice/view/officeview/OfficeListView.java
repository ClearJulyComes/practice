package com.example.practice.view.officeview;

import lombok.Data;

@Data
public class OfficeListView {
    private int id;
    private String name;
    private Boolean isActive;

    public OfficeListView(int id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

}
