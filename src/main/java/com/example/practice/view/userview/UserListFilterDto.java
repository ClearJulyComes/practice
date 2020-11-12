package com.example.practice.view.userview;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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
