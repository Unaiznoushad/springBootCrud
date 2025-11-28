package com.example.java_crud.dto.response;
import lombok.Data;

@Data
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private int durationDays;
    // Nested response to show which institute this belongs to (optional)
    private String instituteName;
}