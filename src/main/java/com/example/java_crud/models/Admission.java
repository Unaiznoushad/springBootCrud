package com.example.java_crud.models;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "admission")
public class Admission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate admissionDate;
    private LocalDate completionDate;

    // getters/setters
    public Long getAdmissionId() { return admissionId;}
    public void setAdmissionId(Long admissionId) { this.admissionId = admissionId;}
    public Student getStudent() { return student;}
    public void setStudent(Student student) { this.student = student;}
    public Institute getInstitute() { return institute;}
    public void setInstitute(Institute institute) { this.institute = institute;}
    public Course getCourse() { return course;}
    public void setCourse(Course course) { this.course = course;}
    public LocalDate getAdmissionDate() { return admissionDate;}
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate;}
    public LocalDate getCompletionDate() { return completionDate;}
    public void setCompletionDate(LocalDate completionDate) { this.completionDate = completionDate;}
}
