package com.example.practice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "User_Doc")
public class UserDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "doc_number", nullable = false)
    private int docNumber;

    @Column(name = "doc_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserInfo userId;

    @ManyToOne
    @JoinColumn(name = "doc_code")
    private Doc docCode;

    public UserDoc(){

    }

    public UserDoc(int id, int docNumber, Date docDate, UserInfo userId, Doc docCode) {
        this.id = id;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.userId = userId;
        this.docCode = docCode;
    }
}
