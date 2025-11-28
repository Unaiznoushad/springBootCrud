package com.example.java_crud.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"_class", "admissionId", "admissionDate", "completionDate",
        "student", "institute", "course", "createdAt", "updatedAt"})
public class AdmissionResponse {
    @JsonProperty("_class")
    private final String classType = "com.example.java_crud.models.Admission";

    private Long admissionId;
    private LocalDate admissionDate;
    private LocalDate completionDate;

    // Nested DTOs (using minimal response types)
    private StudentResponseMinimal student;
    private InstituteResponseMinimal institute;
    private CourseResponseMinimal course;

    private LocalDateTime createdAt; // Assuming you added these to Admission entity
    private LocalDateTime updatedAt;
}