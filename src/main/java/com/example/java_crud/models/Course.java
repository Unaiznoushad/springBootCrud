package com.example.java_crud.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "course")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private int durationDays;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
    public Long getCourseId() { return courseId;}
    public void setCourseId(Long courseId) { this.courseId = courseId;}
    public String getCourseName() { return courseName;}
    public void setCourseName(String courseName) { this.courseName = courseName;}
    public int getDurationDays() { return durationDays;}
    public void setDurationDays(int durationDays) { this.durationDays = durationDays;}
    public Institute getInstitute() { return institute;}
    public void setInstitute(Institute institute) { this.institute = institute;}
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // 🔑 FIX 2: Add Getters/Setters for updatedAt
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
