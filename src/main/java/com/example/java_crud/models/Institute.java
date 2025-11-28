package com.example.java_crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "institute")
public class Institute {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instituteId;

    private String instituteName;
    private String address;
    private String contactNo;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses;

    public Long getInstituteId() { return instituteId; }
    public void setInstituteId(Long instituteId) { this.instituteId = instituteId; }
    public String getInstituteName() { return instituteName; }
    public void setInstituteName(String instituteName) { this.instituteName = instituteName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // 🔑 FIX: Add Getters/Setters for updatedAt
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
