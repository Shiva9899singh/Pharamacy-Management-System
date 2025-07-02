package com.pharmacy.AuthService.service;

import com.pharmacy.AuthService.config.CustomUserDetails;
import com.pharmacy.AuthService.entity.User;
import com.pharmacy.AuthService.exception.UserNotFoundException;
import com.pharmacy.AuthService.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> existinguser = this.authRepository.findByUsername(username);
        return existinguser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UserNotFoundException("User not found with name : "
                        + username));
    }
}
