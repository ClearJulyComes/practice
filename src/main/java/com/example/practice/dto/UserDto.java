package com.example.practice.dto;

import com.example.practice.model.Country;
import com.example.practice.model.Office;
import com.example.practice.model.UserDoc;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String phone;
    private String position;
    private Boolean isIdentified;
    private Integer officeId;
    private Integer citizenshipCode;
    private String citizenshipName;
    private Integer docCode;
    private String docName;
    private String docNumber;
    private Date docDate;

    public UserDto(int id, String firstName, Boolean isIdentified, Integer officeId, Integer citizenshipCode,
                   String citizenshipName, Integer docCode, String docName, String docNumber, Date docDate) {
        this.id = id;
        this.firstName = firstName;
        this.isIdentified = isIdentified;
        this.officeId = officeId;
        this.citizenshipCode = citizenshipCode;
        this.citizenshipName = citizenshipName;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
    }
}
