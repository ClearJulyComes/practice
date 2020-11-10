package com.example.practice.view.organizationview;

import lombok.Data;
import lombok.NonNull;

@Data
public class OrganizationListFilterDto {
    @NonNull
    private String name;
    private String inn;
    private Boolean isActive;

    public OrganizationListFilterDto(@NonNull String name) {
        this.name = name;
    }

    public OrganizationListFilterDto() {

    }
}
