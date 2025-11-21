package com.example.java_crud.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    private String course_name;
    private int duration_days;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
}
