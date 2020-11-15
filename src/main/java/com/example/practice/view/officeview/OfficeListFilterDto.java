package com.example.practice.view.officeview;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OfficeListFilterDto {
    @NotNull
    private Integer orgId;
    private String name;
    private String phone;
    private Boolean isActive;

    public OfficeListFilterDto(@NotNull Integer orgId, String name) {
        this.orgId = orgId;
        this.name = name;
    }

    public OfficeListFilterDto() {
    }
}
