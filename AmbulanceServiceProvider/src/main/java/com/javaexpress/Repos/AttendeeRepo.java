package com.javaexpress.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaexpress.Models.Attendee;

public interface AttendeeRepo extends JpaRepository<Attendee, Long> {
}
