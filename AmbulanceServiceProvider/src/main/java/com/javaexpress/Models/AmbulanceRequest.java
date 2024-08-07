package com.javaexpress.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ambulance_requests")
@Getter
@Setter
public class AmbulanceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ambulance_id", nullable = false)
	private Ambulance ambulance;

	@NotNull
	@Column(nullable = false)
	private String status = "PENDING"; // e.g., PENDING, ASSIGNED, COMPLETED

	@ManyToOne
	@JoinColumn(name = "attendee_id")
	private Attendee attendee;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Employee driver;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // Assuming you have a User entity
}
