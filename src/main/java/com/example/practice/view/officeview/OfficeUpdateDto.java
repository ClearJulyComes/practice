package com.example.practice.view.officeview;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OfficeUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    private String phone;
    private Boolean isActive;
}
