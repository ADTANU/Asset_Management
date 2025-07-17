package com.hexaware.asset.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.hexaware.asset.config.JwtService;
import com.hexaware.asset.entity.AuthRequest;
import com.hexaware.asset.entity.User;
import com.hexaware.asset.exception.ResourceNotFoundException;
import com.hexaware.asset.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        userService.register(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtService.generateToken(authRequest.getEmail());

        Optional<User> optionalUser = userService.findByEmail(authRequest.getEmail());
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + authRequest.getEmail()));

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
