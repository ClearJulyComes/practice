package com.example.practice.view.userview;

import lombok.Data;

import java.time.LocalDate;


@Data
public class UserIdView {
    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String phone;
    private String position;
    private Boolean isIdentified;
    private Integer citizenshipCode;
    private String citizenshipName;
    private String docName;
    private String docNumber;
    private String docDate;

    public UserIdView(int id, String firstName, Boolean isIdentified, Integer citizenshipCode,
                      String citizenshipName, String docName, String docNumber, String docDate) {
        this.id = id;
        this.firstName = firstName;
        this.isIdentified = isIdentified;
        this.citizenshipCode = citizenshipCode;
        this.citizenshipName = citizenshipName;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
    }

    public UserIdView() {
    }
}
