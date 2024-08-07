package com.javaexpress.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.Models.Attendee;
import com.javaexpress.Repos.AttendeeRepo;

@Service
public class attendeeService {

	@Autowired
	private AttendeeRepo attendeeRepository;

	public Attendee addAttendee(Attendee attendee) {
		return attendeeRepository.save(attendee);
	}
	
	public List<Attendee> getAllAttendees() {
		return attendeeRepository.findAll();
	}

}
