package com.example.java_crud.models;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {
    private String classId;
    private String className;
    private String section;
    private String academicYear;
}