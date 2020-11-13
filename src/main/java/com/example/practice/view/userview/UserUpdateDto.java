package com.example.practice.view.userview;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserUpdateDto {
    @NotNull
    private int id;
    private Integer officeId;
    @NotNull
    private String firstName;
    private String secondName;
    private String middleName;
    private String phone;
    @NotNull
    private String position;
    private Boolean isIdentified;
    private Integer citizenshipCode;
    private String docName;
    private String docNumber;
    private Date docDate;
}
