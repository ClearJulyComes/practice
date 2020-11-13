package com.example.practice.view.organizationview;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrganizationUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String fullName;
    @NotNull
    private String inn;
    @NotNull
    private String kpp;
    @NotNull
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

    public OrganizationUpdateDto() {

    }
}
