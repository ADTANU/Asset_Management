package com.hexaware.asset.repository;

import java.util.Optional;

import com.hexaware.asset.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}