package com.example.practice.dto;

import lombok.Data;

@Data
public class OfficeDto {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Boolean isActive;
    private int orgId;

    public OfficeDto(int id, String name, String phone, String address, Boolean isActive, int orgId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
        this.orgId = orgId;
    }
}
