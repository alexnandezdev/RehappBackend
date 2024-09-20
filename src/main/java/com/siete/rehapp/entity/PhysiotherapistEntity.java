package com.siete.rehapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "physiotherapists")
public class PhysiotherapistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "professional_card_number", nullable = false)
    private String professionalCardNumber;

    @ManyToMany
    @JoinTable(
            name = "physiotherapist_patient",
            joinColumns = @JoinColumn(name = "physiotherapist_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<UserEntity> patients;

}
