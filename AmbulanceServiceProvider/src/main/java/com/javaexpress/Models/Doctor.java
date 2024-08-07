package com.javaexpress.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Specialty cannot be null")
    @Column(nullable = false)
    private String specialty;
}
