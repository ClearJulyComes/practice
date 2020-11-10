package com.example.practice.view.organizationview;

import lombok.Data;
import lombok.NonNull;

@Data
public class OrganizationUpdateDto {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String fullName;
    @NonNull
    private String inn;
    @NonNull
    private String kpp;
    @NonNull
    private String address;
    private String phone;
    private Boolean isActive;

    public OrganizationUpdateDto(int id, String name, String fullName, String inn, String kpp, String address) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }
    public OrganizationUpdateDto(){

    }
}
