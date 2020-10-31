package com.example.practice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Collection;

@Data
@Entity
@Table(name = "Office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Office orgId;

    //@OneToMany(mappedBy = "officeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Collection<UserInfo> userInfos;

    public Office(){

    }

    public Office(int id, Office orgId) {
        this.id = id;
        this.orgId = orgId;
    }
}
