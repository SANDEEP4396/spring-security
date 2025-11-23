package com.spring.security.repository;

import com.spring.security.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
