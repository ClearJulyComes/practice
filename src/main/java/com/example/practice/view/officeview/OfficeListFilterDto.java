package com.example.practice.view.officeview;

import lombok.Data;
import lombok.NonNull;

@Data
public class OfficeListFilterDto {
    @NonNull
    private Integer orgId;
    private String name;
    private String phone;
    private Boolean isActive;

    public OfficeListFilterDto(){

    }
}
