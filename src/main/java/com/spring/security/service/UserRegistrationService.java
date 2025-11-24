package com.spring.security.service;

import com.spring.security.entity.RegisterUserRequest;
import com.spring.security.entity.UserResponse;
import com.spring.security.entity.Users;
import com.spring.security.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(final UserDetailRepository userDetailRepository,
                                   final PasswordEncoder passwordEncoder) {
        this.userDetailRepository = userDetailRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest request){
        if(userDetailRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("User already exists");
        }
        Users users = new Users();

        users.setUsername(request.getUsername());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setRole(request.getRole());
        Users registeredUser =userDetailRepository.save(users);

        return new UserResponse(registeredUser.getId(),registeredUser.getUsername(),registeredUser.getRole().toString());
    }
}
