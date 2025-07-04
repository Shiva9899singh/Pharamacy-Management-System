package com.pharmacy.AuthService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pharmacy.AuthService.entity.User;
import com.pharmacy.AuthService.exception.UserAlreadyExistsException;
import com.pharmacy.AuthService.exception.UserNotFoundException;
import com.pharmacy.AuthService.repository.AuthRepository;
import com.pharmacy.AuthService.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private AuthRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Register user
    public User registerUser(User user) {
        Optional<User> existingUser = this.userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with username '" + user.getUsername() + "' already exists.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            return this.userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while registering user with username: " + user.getUsername(), e);
        }
    }

    // Get user by username and throw if not found
    public Optional<User> getUser(String username) {
        Optional<User> existingUser = this.userRepository.findByUsername(username);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User with username '" + username + "' not found.");
        }
        return existingUser;
    }

    // Generate JWT token
    public String generateToken(String username, String role) {
        try {
            return jwtUtil.generateToken(username, role);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while generating token for user: " + username, e);
        }
    }

    // Validate JWT token
    public void validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e) {
            throw new RuntimeException("Token validation failed.", e);
        }
    }


    // Direct get without exception
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
