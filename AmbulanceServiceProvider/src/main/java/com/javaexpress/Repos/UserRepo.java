package com.javaexpress.Repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByUserType(String userType);
}
