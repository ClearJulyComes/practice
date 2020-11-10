package com.example.practice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "Organization")
@NamedQuery(name = "findById", query = "SELECT o FROM Organization o WHERE o.id = :id")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "inn", nullable = false, length = 15)
    private String inn;

    @Column(name = "kpp", nullable = false, length = 15)
    private String kpp;

    @Column(name = "address", nullable = false, length = 80)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    public Organization() {

    }

    public Organization(int id, int version, String name, String fullName, String inn, String kpp, String address) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }
}
