package com.example.practice.view.userview;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserSaveDto {
    @NotNull
    private Integer officeId;
    @NotNull
    private String firstName;
    private String secondName;
    private String middleName;
    private String phone;
    @NotNull
    private String position;
    @NotNull
    private Boolean isIdentified;
    private Integer citizenshipCode;
    private Integer docCode;
    private String docName;
    private String docNumber;
    private LocalDate docDate;

    public UserSaveDto(@NotNull Integer officeId, @NotNull String firstName, @NotNull String position,
                       @NotNull Boolean isIdentified) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.position = position;
        this.isIdentified = isIdentified;
    }

    public UserSaveDto() {
    }
}
