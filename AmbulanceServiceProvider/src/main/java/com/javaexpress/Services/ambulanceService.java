package com.javaexpress.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.Models.Ambulance;
import com.javaexpress.Models.AmbulanceRequest;
import com.javaexpress.Models.Attendee;
import com.javaexpress.Models.Employee;
import com.javaexpress.Models.User;
import com.javaexpress.Repos.AmbulanceRepo;
import com.javaexpress.Repos.AmbulanceRequestRepo;
import com.javaexpress.Repos.AttendeeRepo;
import com.javaexpress.Repos.EmployeeRepo;
import com.javaexpress.Repos.UserRepo;

@Service
public class ambulanceService {

	@Autowired
	private AmbulanceRepo ambulanceRepository;

	@Autowired
	private AmbulanceRequestRepo ambulanceRequestRepo;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private AttendeeRepo attendeeRepository;

	@Autowired
	private EmployeeRepo employeeRepository;

	public Ambulance addAmbulance(Ambulance ambulance) {
		return ambulanceRepository.save(ambulance);
	}

	public List<Ambulance> getAllAmbulances() {
		return ambulanceRepository.findAll();
	}

	public Ambulance updateAmbulance(Long id, Ambulance ambulanceDetails) {
		Ambulance ambulance = ambulanceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ambulance not found with id: " + id));
		ambulance.setLicensePlate(ambulanceDetails.getLicensePlate());
		ambulance.setStatus(ambulanceDetails.getStatus());
		ambulance.setLocation(ambulanceDetails.getLocation());
		return ambulanceRepository.save(ambulance);
	}

	public AmbulanceRequest requestAmbulance(Long ambulanceId, String username) {
		Ambulance ambulance = ambulanceRepository.findById(ambulanceId)
				.orElseThrow(() -> new RuntimeException("Ambulance not found with id: " + ambulanceId));

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found with username: " + username));

		if (!ambulance.getStatus().equals("AVAILABLE")) {
			throw new RuntimeException("Ambulance is not available");
		}

		ambulance.setStatus("ON_CALL");

		AmbulanceRequest request = new AmbulanceRequest();
		request.setAmbulance(ambulance);
		request.setUser(user);
		request.setStatus("PENDING");

		ambulanceRepository.save(ambulance);
		return ambulanceRequestRepo.save(request);
	}

	public AmbulanceRequest getAmbulanceRequestById(Long id) {
		Optional<AmbulanceRequest> optionalRequest = ambulanceRequestRepo.findById(id);
		if (optionalRequest.isPresent()) {
			return optionalRequest.get();
		} else {
			throw new RuntimeException("Request not found");
		}
	}

	public AmbulanceRequest getAmbulanceRequestByIdAndUser(Long id, String username) {
		Optional<AmbulanceRequest> optionalRequest = ambulanceRequestRepo.findById(id);
		if (optionalRequest.isPresent()) {
			AmbulanceRequest request = optionalRequest.get();
			if (request.getUser().getUsername().equals(username)) {
				return request;
			} else {
				throw new RuntimeException("Access denied: Not your request");
			}
		} else {
			throw new RuntimeException("Request not found");
		}
	}

	public List<AmbulanceRequest> getAllAmbulancesRequest() {
		return ambulanceRequestRepo.findAll();
	}

	public AmbulanceRequest updateRequest(Long requestId, Long attendeeId, Long driverId) {
		AmbulanceRequest request = ambulanceRequestRepo.findById(requestId)
				.orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));

		Attendee attendee = attendeeRepository.findById(attendeeId)
				.orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));

		if (!"AVAILABLE".equalsIgnoreCase(attendee.getStatus())) {
			throw new RuntimeException("Attendee with id: " + attendeeId + " is not available");
		}

		Employee driver = employeeRepository.findById(driverId)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + driverId));

		if (!"Driver".equalsIgnoreCase(driver.getSpecialty())) {
			throw new RuntimeException("Employee with id: " + driverId + " is not a driver");
		}

		if (!"AVAILABLE".equalsIgnoreCase(driver.getStatus())) {
			throw new RuntimeException("Driver with id: " + driverId + " is not available");
		}

		// Update the status to UNAVAILABLE
		attendee.setStatus("UNAVAILABLE");
		driver.setStatus("UNAVAILABLE");

		attendeeRepository.save(attendee);
		employeeRepository.save(driver);

		request.setAttendee(attendee);
		request.setDriver(driver);
		request.setStatus("ASSIGNED");

		Ambulance ambulance = request.getAmbulance();
		ambulance.setStatus("IN_SERVICE");
		ambulanceRepository.save(ambulance);

		return ambulanceRequestRepo.save(request);
	}

}
