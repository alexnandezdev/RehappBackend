package com.siete.rehapp.entity;

import com.siete.rehapp.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        //@UniqueConstraint(columnNames = "identification_number")
})
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @ManyToMany(mappedBy = "patients", fetch = FetchType.EAGER)
    private Set<PhysiotherapistEntity> physiotherapists;

}
