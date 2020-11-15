package com.example.practice.view.userview;

import lombok.Data;

@Data
public class UserListView {
    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;

    public UserListView(int id, String firstName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.position = position;
    }

    public UserListView() {
    }
}
