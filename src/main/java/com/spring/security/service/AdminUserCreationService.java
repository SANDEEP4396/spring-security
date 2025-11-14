package com.spring.security.service;

import com.spring.security.entity.Users;
import com.spring.security.repository.UserDetailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserCreationService {

    @Bean
    public CommandLineRunner createAdminUser(UserDetailRepository userDetailRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userDetailRepository.findByUsername("admin").isEmpty()){
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole("ADMIN");
                userDetailRepository.save(admin);
                System.out.println("Admin created successfully");
            }
        };
    }
}
