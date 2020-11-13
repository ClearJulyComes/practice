package com.example.practice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "User_Doc")
public class UserDoc {

    @Id
    private Integer id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "doc_number", nullable = false, length = 30)
    private String docNumber;

    @Column(name = "doc_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    public UserDoc() {

    }

    public UserDoc(String docNumber, Date docDate, Doc doc) {
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.doc = doc;
    }

}
