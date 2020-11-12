package com.example.practice.view.organizationview;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class OrganizationIdView {
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

    public OrganizationIdView(int id, String name, String fullName, String inn, String kpp, String address) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }

    public OrganizationIdView() {
    }
}
