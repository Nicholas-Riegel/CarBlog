package com.car_blog.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.car_blog.backend.entity.User;
import com.car_blog.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String email, String rawPassword){
        if (userRepo.findByUsername(username).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }
        if (userRepo.findByEmail(email).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        return userRepo.save(new User(username, email, encodedPassword));
    }

    public User getUserById(Long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}