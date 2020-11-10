package com.example.practice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "Doc")
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", unique = true)
    private int code;

    @Column(name = "name", length = 60, nullable = false, unique = true)
    private String name;

    public Doc(){

    }

    public Doc(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
