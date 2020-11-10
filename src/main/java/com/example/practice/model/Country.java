package com.example.practice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", unique = true)
    private int code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Country() {

    }

    public Country(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
