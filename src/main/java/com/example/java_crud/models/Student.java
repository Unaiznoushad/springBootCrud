package com.example.java_crud.models;


//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "student")
//public class Student {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long studentId;
//
//    private String name;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//    private String password;
//    private String role;
//
//    private String phone;
//
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    // getters and setters
//    public Long getStudentId() { return studentId;}
//    public void setStudentId(Long studentId) { this.studentId = studentId;}
//    public String getName() { return name;}
//    public void setName(String name) { this.name = name;}
//    public String getEmail() { return email;}
//    public void setEmail(String email) { this.email = email;}
//    public String getPassword() { return password;}
//    public void setPassword(String password) { this.password = password;}
//    public String getPhone() { return phone;}
//    public void setPhone(String phone) { this.phone = phone;}
//    public String getRole() { return role; }
//    public void setRole(String role) { this.role = role; }
//    public LocalDateTime getCreatedAt() { return createdAt;}
//    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;}
//}
//
// ... imports ...


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Entity
@Table(name = "student")
@Data // Use Lombok for getters/setters/constructors if possible
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    // Updated name fields
    private String firstName;
    private String lastName;

    // Updated ID format for response
    private String studentSystemId; // STU1023

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String phoneNumber; // Renamed from phone

    private LocalDate dateOfBirth; // New
    private String gender; // New

    @Embedded
    private Address address; // Nested Address

    @Embedded
    private ClassInfo classInfo; // Nested ClassInfo

    private String role; // ROLE_STUDENT or ROLE_ADMIN
    private String status = "ACTIVE"; // Default status
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt; // Use setters to update this

    // Getters/Setters (omitted here if using Lombok @Data)
}