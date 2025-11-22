package com.spring.security.controller;

import com.spring.security.entity.Users;
import com.spring.security.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @GetMapping("/health")
    public String health() {
        return "HEALTHY";
    }

    @GetMapping("/users")
    public List<Users> userDetails() {
        return userDetailRepository.findAll();
    }

    @GetMapping("/userDepartment")
    public Optional<Users> userDepartment(@RequestParam Long id){
        return userDetailRepository.findById(id);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody Users users){
        Users usersAdded = userDetailRepository.save(users);
        return usersAdded.getUsername() + usersAdded.getId();
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userDetailRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
