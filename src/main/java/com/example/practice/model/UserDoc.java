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

    @Column(name = "doc_number", nullable = false, length = 30)
    private String docNumber;

    @Column(name = "doc_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Doc docId;

    public UserDoc(){

    }

    public UserDoc(int id, String docNumber, Date docDate, Doc docId) {
        this.id = id;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.docId = docId;
    }
}
