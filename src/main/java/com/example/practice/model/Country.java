package com.example.practice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Collection;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "code")
    private int code;
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    //@OneToMany(mappedBy = "citizenshipCode", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Collection<UserInfo> userInfos;

    public Country(){

    }

    public Country(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
