package com.multi.model.dto.tmddk;

import java.time.LocalDateTime;

public class User {
    private String userId;       // 사용자 아이디 (길이 10)
    private String role;         // 사용자 역할 ('admin' / 'general')
    private String name;         // 사용자 이름
    private String password;     // 사용자 비밀번호
    private LocalDateTime createdAt;  // 생성일자
    private LocalDateTime updatedAt;  // 변경일자

    // 기본 생성자, getter/setter, toString

    public User() {
    }

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public User(String userId, String role, String name, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.role = role;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
