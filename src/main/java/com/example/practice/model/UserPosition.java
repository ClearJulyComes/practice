package com.example.practice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Collection;

@Data
@Entity
@Table(name = "User_Position")
public class UserPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "position", nullable = false, unique = true)
    private String position;

    //@OneToMany(mappedBy = "positionId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Collection<UserInfo> userInfos;

    public UserPosition(){

    }

    public UserPosition(int id, String position) {
        this.id = id;
        this.position = position;
    }
}
