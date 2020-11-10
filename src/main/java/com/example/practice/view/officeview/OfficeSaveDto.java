package com.example.practice.view.officeview;

import lombok.Data;
import lombok.NonNull;

@Data
public class OfficeSaveDto {
    @NonNull
    private Integer orgId;
    private String name;
    private String phone;
    private String address;
    private Boolean isActive = true;

    public OfficeSaveDto(){}

}
