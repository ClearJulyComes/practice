package com.example.practice.view.officeview;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class OfficeSaveDto {
    @NotNull
    private Integer orgId;
    private String name;
    private String phone;
    private String address;
    private Boolean isActive = true;

    public OfficeSaveDto() {
    }

}
