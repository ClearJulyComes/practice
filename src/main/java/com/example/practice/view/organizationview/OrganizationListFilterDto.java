package com.example.practice.view.organizationview;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrganizationListFilterDto {
    @NotNull
    private String name;
    private String inn;
    private Boolean isActive;

    public OrganizationListFilterDto(String name) {
        this.name = name;
    }

    public OrganizationListFilterDto() {

    }
}
