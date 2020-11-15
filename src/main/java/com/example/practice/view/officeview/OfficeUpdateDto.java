package com.example.practice.view.officeview;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class OfficeUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    private String phone;
    private Boolean isActive;

    public OfficeUpdateDto() {
    }

    public OfficeUpdateDto(@NotNull int id, @NotNull String name, @NotNull String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
