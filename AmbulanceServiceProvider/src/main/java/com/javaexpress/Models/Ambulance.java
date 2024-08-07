package com.javaexpress.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ambulances")
@Getter
@Setter
public class Ambulance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String licensePlate;

	@Column(nullable = false)
	private String status = "AVAILABLE"; // e.g., AVAILABLE, ON_CALL, IN_SERVICE

	@Column(nullable = false)
	private String location; // Current location or area
}