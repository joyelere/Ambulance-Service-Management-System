package com.javaexpress.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.Models.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
