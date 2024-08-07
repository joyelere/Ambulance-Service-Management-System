package com.javaexpress.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.Models.AmbulanceRequest;

public interface AmbulanceRequestRepo extends JpaRepository<AmbulanceRequest, Long> {
}
