package com.example.practice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Collection;

@Data
@Entity
@Table(name = "Doc")
public class Doc {
    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "name", length = 60, nullable = false, unique = true)
    private String name;

    //@OneToMany(mappedBy = "docCode", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Collection<UserDoc> userDocs;

    public Doc(){

    }

    public Doc(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
