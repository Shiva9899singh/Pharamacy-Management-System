//package com.druginventoryservice.service.impl;
//
//import com.druginventoryservice.entity.User;
//import com.druginventoryservice.exception.UserAlreadyExistsException;
//import com.druginventoryservice.exception.UserNotFoundException;
//import com.druginventoryservice.repository.UserRepository;
//import com.druginventoryservice.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public User registerUser(User user) {
//        Optional<User> existingUser = this.userRepo.findByUsername(user.getUsername());
//        if (existingUser.isPresent()) {
//            throw new UserAlreadyExistsException("User with username '" + user.getUsername() + "' already exists.");
//        }
//
//        try {
//            return this.userRepo.save(user);
//        } catch (Exception e) {
//            throw new UserNotFoundException("Unexpected error while registering user with : " + user.getUsername());
//        }
//    }
//}
