package com.example.practice.view.userview;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private LocalDate docDate;

    public UserUpdateDto(@NotNull int id, @NotNull String firstName, @NotNull String position) {
        this.id = id;
        this.firstName = firstName;
        this.position = position;
    }

    public UserUpdateDto() {
    }
}
