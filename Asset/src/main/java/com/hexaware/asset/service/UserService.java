package com.hexaware.asset.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.hexaware.asset.entity.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);
    void deleteUser(Long id);
    ResponseEntity<?> register(User user);
	void softDeleteUser(Long id);
}
