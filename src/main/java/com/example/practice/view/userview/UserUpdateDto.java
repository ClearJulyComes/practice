package com.example.practice.view.userview;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
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
