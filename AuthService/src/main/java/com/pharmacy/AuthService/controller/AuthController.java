package com.pharmacy.AuthService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.AuthService.dto.AuthRequest;
import com.pharmacy.AuthService.entity.User;
import com.pharmacy.AuthService.exception.InvalidCredentialsException;
import com.pharmacy.AuthService.exception.UserNotFoundException;
import com.pharmacy.AuthService.service.AuthService;
import com.pharmacy.AuthService.service.CustomUserDetailsService;

import jakarta.validation.Valid;

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
    @GetMapping("/user-by-username")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username) {
        Optional<User> user = authService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                   .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
    @GetMapping("/email/{username}")
    public ResponseEntity<String> getEmailByUsername(@PathVariable String username) {
        return authService.getUserByUsername(username)
                .map(user -> ResponseEntity.ok(user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
    }
    



    


}
