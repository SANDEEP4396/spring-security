package com.spring.security.repository;

import com.spring.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String userName);
}
