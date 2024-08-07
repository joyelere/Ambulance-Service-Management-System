package com.javaexpress.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.Models.Ambulance;

public interface AmbulanceRepo extends JpaRepository<Ambulance, Long> {
}
