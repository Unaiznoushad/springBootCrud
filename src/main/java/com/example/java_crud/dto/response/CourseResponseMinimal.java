package com.example.java_crud.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "_class", "courseId", "courseName", "durationDays", "instituteId", "createdAt", "updatedAt" })
public class CourseResponseMinimal {

    @JsonProperty("_class")
    private final String classType = "com.example.java_crud.models.Course";

    private Long courseId; // Maps to courseId
    private String courseName;
    private Integer durationDays;
    private String instituteName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}