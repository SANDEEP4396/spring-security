package com.spring.security.controller;

import com.spring.security.entity.UserLog;
import com.spring.security.entity.Users;
import com.spring.security.repository.UserDetailRepository;
import com.spring.security.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private UserLogRepository userLogRepository;


    @GetMapping("/health")
    public String health() {
        return "HEALTHY";
    }

    @GetMapping("/users")
    //This annotation will be intercepted by AOP. It will be handled by MethodSecurityExpressionHandler.
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<Users> getUserDetails() {
        return userDetailRepository.findAll();
    }

    @GetMapping("/userDepartment")
    @PreAuthorize("hasAuthority('USER_READ')")
    public Optional<Users> getUserDepartment(@RequestParam Long id){
        return userDetailRepository.findById(id);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('USER_WRITE')")
   // @PreAuthorize("hasRole('USER')")
    public String addUser(@RequestBody Users users){
        Users usersAdded = userDetailRepository.save(users);
        return usersAdded.getUsername()+ "_"+ usersAdded.getId();

    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deleteUser(@RequestParam Long id) {
        userDetailRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @PostMapping
    public UserLog createLog(@RequestBody UserLog log, Principal principal){
        log.setPerformedBy(principal.getName());
        log.setTimestamp(LocalDateTime.now());
        return userLogRepository.save(log);
    }

    @GetMapping("/userLogs")
    @PostAuthorize("returnObject.performedBy == authentication.name")
    public UserLog getLogsById(@RequestParam Long id){
        return userLogRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Log not found"));
    }
}
