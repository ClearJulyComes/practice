package com.example.practice.view.userview;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserListFilterDto {
    @NotNull
    private Integer officeId;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private Integer citizenshipCode;
    private Integer docCode;

    public UserListFilterDto() {
    }

    public UserListFilterDto(@NotNull Integer officeId, String firstName) {
        this.officeId = officeId;
        this.firstName = firstName;
    }
}
