package com.example.practice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "User_Info")
@NamedQuery(name = "findUserById", query = "SELECT u FROM UserInfo u WHERE u.id= :id")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "position", nullable = false, length = 20)
    private String position;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_info_id")
    private UserDoc userDoc;

    public UserInfo() {

    }

    public UserInfo(int id, String firstName, Office office) {
        this.id = id;
        this.firstName = firstName;
        this.office = office;
    }
}
