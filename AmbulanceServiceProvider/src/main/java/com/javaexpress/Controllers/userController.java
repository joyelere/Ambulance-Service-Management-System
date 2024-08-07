package com.javaexpress.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.Models.Ambulance;
import com.javaexpress.Models.AmbulanceRequest;
import com.javaexpress.Models.Attendee;
import com.javaexpress.Models.Doctor;
import com.javaexpress.Models.Employee;
import com.javaexpress.Models.User;
import com.javaexpress.Services.*;
import com.javaexpress.dto.UpdateRequestDto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class userController {

	@Autowired
	private userService userService;

	@Autowired
	private ambulanceService ambulanceService;

	@Autowired
	private doctorService doctorService;

	@Autowired
	private attendeeService attendeeService;

	@Autowired
	private employeeService employeeService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/type/{userType}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> getUsersByType(@PathVariable String userType) {
		return userService.getUsersByType(userType);
	}

	@GetMapping("/{username}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Optional<User> getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@GetMapping("/doctor")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Doctor> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/attendee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Attendee> getAllAttendees() {
		return attendeeService.getAllAttendees();
	}

	@GetMapping("/employee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/ambulance")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Ambulance> getAllAmbulances() {
		return ambulanceService.getAllAmbulances();
	}

	@PutMapping("/ambulance/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Ambulance> updateUser(@PathVariable Long id, @Valid @RequestBody Ambulance ambulanceDetails) {
		try {
			Ambulance updatedAmbulance = ambulanceService.updateAmbulance(id, ambulanceDetails);
			return ResponseEntity.ok(updatedAmbulance);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/ambulance/request/{ambulanceId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<AmbulanceRequest> requestAmbulance(@PathVariable Long ambulanceId, Principal principal) {
		try {
			String username = principal.getName();
			AmbulanceRequest ambulanceRequest = ambulanceService.requestAmbulance(ambulanceId, username);
			return ResponseEntity.ok(ambulanceRequest);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("/ambulance/request/{ambulanceRequestId}")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<AmbulanceRequest> getAmbulanceRequest(@PathVariable Long ambulanceRequestId,
			Principal principal) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

			boolean isAdmin = authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

			if (isAdmin) {
				// Admin can access any request
				AmbulanceRequest ambulanceRequest = ambulanceService.getAmbulanceRequestById(ambulanceRequestId);
				return ResponseEntity.ok(ambulanceRequest);
			} else {
				// Only allow users to access their own requests
				String username = principal.getName();
				AmbulanceRequest ambulanceRequest = ambulanceService.getAmbulanceRequestByIdAndUser(ambulanceRequestId,
						username);
				return ResponseEntity.ok(ambulanceRequest);
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@GetMapping("/ambulance/request")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public List<AmbulanceRequest> getAllAmbulancesRequest() {
		return ambulanceService.getAllAmbulancesRequest();
	}

	@PutMapping("/ambulance/request/{ambulanceRequestId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AmbulanceRequest> updateRequest(@PathVariable Long ambulanceRequestId,
			@RequestBody UpdateRequestDto updateRequestDto) {
		try {
			AmbulanceRequest updatedRequest = ambulanceService.updateRequest(ambulanceRequestId,
					updateRequestDto.getAttendeeId(), updateRequestDto.getDriverId());
			return ResponseEntity.ok(updatedRequest);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}