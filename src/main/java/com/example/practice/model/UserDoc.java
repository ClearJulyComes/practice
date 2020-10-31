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
import javax.persistence.OneToOne;
import java.sql.Date;

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
    private Date docDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserInfo userId;

    @ManyToOne
    @JoinColumn(name = "doc_name")
    private Doc docName;

    public UserDoc(){

    }

    public UserDoc(int id, int docNumber, Date docDate, UserInfo userId, Doc docName) {
        this.id = id;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.userId = userId;
        this.docName = docName;
    }
}
