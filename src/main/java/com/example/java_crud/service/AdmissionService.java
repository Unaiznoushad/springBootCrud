package com.example.java_crud.service;

import com.example.java_crud.models.Admission;
import com.example.java_crud.models.Course;
import com.example.java_crud.models.Institute;
import com.example.java_crud.models.Student;
import com.example.java_crud.repository.AdmissionRepository;
import com.example.java_crud.repository.CourseRepository;
import com.example.java_crud.repository.InstituteRepository;
import com.example.java_crud.repository.StudentRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Data
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    public Admission takeAdmission(Long studentId, Long instituteId, Long courseId) {

        Student student = studentRepository.findById(studentId).orElseThrow();
        Institute institute = instituteRepository.findById(instituteId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        Admission admission = new Admission();
        admission.setStudent(student);
        admission.setInstitute(institute);
        admission.setCourse(course);

        // LocalDate today
        LocalDate today = LocalDate.now();
        admission.setAdmissionDate(today);

        // completion date = now + durationDays
        LocalDate completion = today.plusDays(course.getDurationDays());
        admission.setCompletionDate(completion);

        return admissionRepository.save(admission);
    }

    public List<Admission> getStudentAdmissions(Long studentId) {
        return admissionRepository.findAll().stream()
                .filter(a -> a.getStudent().getStudentId().equals(studentId))
                .toList();
    }

    public long countStudents(Long courseId) {
        return admissionRepository.findAll().stream()
                .filter(a -> a.getCourse().getCourseId().equals(courseId))
                .count();
    }

    public String remainingDays(Long admissionId) {

        Admission admission = admissionRepository.findById(admissionId)
                .orElse(null);

        if (admission == null) {
            return "Admission ID not found";
        }

        LocalDate today = LocalDate.now();
        LocalDate completion = admission.getCompletionDate();

        long days = ChronoUnit.DAYS.between(today, completion);

        return "Remaining days: " + days;
    }

}
