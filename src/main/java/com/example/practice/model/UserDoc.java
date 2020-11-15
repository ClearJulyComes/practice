package com.example.practice.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Date;

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
    private LocalDate docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @MapsId
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    public UserDoc() {

    }

    public UserDoc(String docNumber, LocalDate docDate) {
        this.docNumber = docNumber;
        this.docDate = docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate.toLocalDate();
    }
}
