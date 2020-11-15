package com.example.practice.view.organizationview;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class OrganizationSaveDto {
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

    public OrganizationSaveDto() {
    }

    public OrganizationSaveDto(@NotNull String name, @NotNull String fullName, @NotNull String inn,
                               @NotNull String kpp, @NotNull String address) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }
}
