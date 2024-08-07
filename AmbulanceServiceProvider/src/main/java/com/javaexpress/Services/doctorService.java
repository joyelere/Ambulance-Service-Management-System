package com.javaexpress.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.Models.Doctor;
import com.javaexpress.Repos.DoctorRepo;

@Service
public class doctorService {

	@Autowired
	private DoctorRepo doctorRepository;

	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}
}
