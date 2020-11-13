package com.example.practice.view.userview;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserListFilterDto {
    @NotNull
    private Integer officeId;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private Integer citizenshipCode;
    private Integer docCode;
}
