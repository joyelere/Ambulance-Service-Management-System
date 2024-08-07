package com.javaexpress.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaexpress.Models.User;
import com.javaexpress.Repos.UserRepo;

@Service
public class userService {

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User authenticateUser(String username, String password) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (passwordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<User> getUsersByType(String userType) {
		return userRepository.findByUserType(userType);
	}

//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username)
//            .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
//    }

	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);

	}
}
	