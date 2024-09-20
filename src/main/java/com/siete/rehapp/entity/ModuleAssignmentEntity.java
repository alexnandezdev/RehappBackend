package com.siete.rehapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "module_assignment")
public class ModuleAssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @ManyToOne
    @JoinColumn(name = "physiotherapist_id", nullable = false)
    private UserEntity physiotherapist;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private ModuleEntity moduleId;

    @Column(name = "date_assignment")
    private LocalDateTime dateAssignment;

    @PrePersist
    protected void onCreate() {
        this.dateAssignment = LocalDateTime.now();
    }

}
