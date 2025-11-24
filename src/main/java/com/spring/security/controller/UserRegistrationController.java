package com.spring.security.controller;

import com.spring.security.entity.RegisterUserRequest;
import com.spring.security.entity.Role;
import com.spring.security.entity.UserResponse;
import com.spring.security.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    @Autowired
    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest request){
        request.setRole(Role.USER);
       UserResponse response = userRegistrationService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<UserResponse> registerByAdmin(@RequestBody RegisterUserRequest request){
        UserResponse userResponse = userRegistrationService.registerUser(request);
        return ResponseEntity.ok(userResponse);
    }
}
