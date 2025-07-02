package com.pharmacy.AuthService.controller;

import com.pharmacy.AuthService.dto.AuthRequest;
import com.pharmacy.AuthService.entity.User;
import com.pharmacy.AuthService.exception.InvalidCredentialsException;
import com.pharmacy.AuthService.service.AuthService;
import com.pharmacy.AuthService.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    @Qualifier("customUserDetailsService")
    private CustomUserDetailsService detailsService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User registeredUser = this.authService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {

            UserDetails userDetails = detailsService.loadUserByUsername(authRequest.getUsername());
            String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");

            String token = authService.generateToken(authRequest.getUsername(), role);
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        else {
            throw new InvalidCredentialsException("Invalid username or password. Please try again.");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return ResponseEntity.ok("Token is valid!!");
    }


}
