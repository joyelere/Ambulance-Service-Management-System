package com.javaexpress.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull(message = "Username cannot be null")
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Column(nullable = false)
    private String role = "USER"; // Default value is USER and cannot be null

    @NotNull(message = "User type cannot be null")
    @Column(nullable = false)
    private String userType; // e.g., HOSPITAL, INDIVIDUAL, PHC, TRAFFIC_PATROL
}
