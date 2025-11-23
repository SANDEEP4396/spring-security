package com.spring.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String performedBy;
    private LocalDateTime timestamp;

    public UserLog(Long id, String action, String performedBy, LocalDateTime timestamp) {
        this.id = id;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }

    public UserLog() {
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", performedBy='" + performedBy + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserLog userLog = (UserLog) o;
        return Objects.equals(id, userLog.id) && Objects.equals(action, userLog.action)
                && Objects.equals(performedBy, userLog.performedBy) && Objects.equals(timestamp, userLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, performedBy, timestamp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
