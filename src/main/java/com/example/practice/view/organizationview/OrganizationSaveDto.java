package com.example.practice.view.organizationview;

import lombok.Data;
import lombok.NonNull;

@Data
public class OrganizationSaveDto {
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
}
