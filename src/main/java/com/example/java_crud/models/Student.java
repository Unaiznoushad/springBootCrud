package com.example.java_crud.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password; // hashed

    private String phone;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters and setters
    public Long getStudentId() { return studentId;}
    public void setStudentId(Long studentId) { this.studentId = studentId;}
    public String getName() { return name;}
    public void setName(String name) { this.name = name;}
    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}
    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password;}
    public String getPhone() { return phone;}
    public void setPhone(String phone) { this.phone = phone;}
    public LocalDateTime getCreatedAt() { return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;}
}
