package com.example.practice.view.officeview;

import lombok.Data;

@Data
public class OfficeIdView {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Boolean isActive;

    public OfficeIdView(int id, String name, String phone, String address, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }
}
