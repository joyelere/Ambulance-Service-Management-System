package com.javaexpress.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attendees")
@Getter
@Setter
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull(message = "Name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Specialty cannot be null")
    @Column(nullable = false)
    private String specialty;
    

    @NotNull(message = "Status cannot be null")
    @Column(nullable = false)
    private String status = "AVAILABLE"; // Default status
}
