package com.example.java_crud.dto.request;
import lombok.Data;

@Data
public class CourseRequest {
    private String courseName;
    private int durationDays;
    private Long instituteId; // We only send the ID, not the whole Institute object
}