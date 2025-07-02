//package com.druginventoryservice.service.impl;
//
//import com.druginventoryservice.entity.User;
//import com.druginventoryservice.exception.UserAlreadyExistsException;
//import com.druginventoryservice.exception.UserNotFoundException;
//import com.druginventoryservice.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Optional;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> existingUser = this..findByUsername(user.getUsername());
//        if (existingUser.isPresent()) {
//            throw new UserAlreadyExistsException("User with username '" + user.getUsername() + "' already exists.");
//        }
//    }
//}
