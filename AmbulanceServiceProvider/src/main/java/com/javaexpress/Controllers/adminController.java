package com.javaexpress.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.Models.Ambulance;
import com.javaexpress.Models.Attendee;
import com.javaexpress.Models.Doctor;
import com.javaexpress.Models.Employee;
import com.javaexpress.Services.doctorService;
import com.javaexpress.Services.employeeService;


import com.javaexpress.Services.ambulanceService;
import com.javaexpress.Services.attendeeService;

@RestController
@RequestMapping("/admin")
public class adminController {

	@Autowired
	private doctorService doctorService;

	@Autowired
	private employeeService employeeService;

	@Autowired
	private attendeeService attendeeService;

	@Autowired
	private ambulanceService ambulanceService;

	@PostMapping("/addDoctor")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor addedDoctor = doctorService.addDoctor(doctor);
		return new ResponseEntity<>(addedDoctor, HttpStatus.CREATED);
	}

	@PostMapping("/addEmployee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee addedEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
	}

	@PostMapping("/addAttendee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Attendee> addAttendee(@RequestBody Attendee attendee) {
		Attendee addedAttendee = attendeeService.addAttendee(attendee);
		return new ResponseEntity<>(addedAttendee, HttpStatus.CREATED);
	}

	@PostMapping("/addAmbulance")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Ambulance> addAmbulance(@RequestBody Ambulance ambulance) {
		Ambulance addedAmbulance = ambulanceService.addAmbulance(ambulance);
		return new ResponseEntity<>(addedAmbulance, HttpStatus.CREATED);
	}

}
