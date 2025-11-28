package com.example.java_crud.service;

import com.example.java_crud.dto.response.AdmissionResponse; // 🔑 Use the new Detailed DTO
import com.example.java_crud.dto.response.CourseResponseMinimal;
import com.example.java_crud.dto.response.InstituteResponseMinimal;
import com.example.java_crud.dto.response.StudentResponseMinimal;
import com.example.java_crud.models.Admission;
import com.example.java_crud.models.Course;
import com.example.java_crud.models.Institute;
import com.example.java_crud.models.Student;
import com.example.java_crud.repository.AdmissionRepository;
import com.example.java_crud.repository.CourseRepository;
import com.example.java_crud.repository.InstituteRepository;
import com.example.java_crud.repository.StudentRepository;

import com.example.java_crud.validation.AppValidator;
// import lombok.Data; // ⚠️ REMOVED: @Data is not suitable for Service classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime; // 🔑 Import LocalDateTime for timestamps
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
// @Data // Removed @Data
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private AppValidator validator;

    // 🔑 UPDATED: Return type is now AdmissionResponseDetailed
    public AdmissionResponse takeAdmission(Long studentId, Long instituteId, Long courseId) {

        // 1. Basic ID Validation (Using AppValidator)
        validator.validateId(studentId, "Student");
        validator.validateId(instituteId, "Institute");
        validator.validateId(courseId, "Course");

        // 2. Fetch Entities
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Institute institute = instituteRepository.findById(instituteId)
                .orElseThrow(() -> new RuntimeException("Institute not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 3. CRITICAL BUSINESS RULE VALIDATION
        if (!course.getInstitute().getInstituteId().equals(instituteId)) {
            throw new IllegalArgumentException(
                    "Course ID " + courseId + " does not belong to Institute ID " + instituteId
            );
        }


        // 4. Create and Save Admission
        Admission admission = new Admission();
        admission.setStudent(student);
        admission.setInstitute(institute);
        admission.setCourse(course);
        admission.setAdmissionDate(LocalDate.now());
        admission.setCompletionDate(LocalDate.now().plusDays(course.getDurationDays()));

        // 🔑 FIX: Set timestamps
        admission.setCreatedAt(LocalDateTime.now());
        admission.setUpdatedAt(LocalDateTime.now());

        Admission savedAdmission = admissionRepository.save(admission);

        // 5. Return AdmissionResponseDetailed DTO
        return mapToDetailedResponse(savedAdmission); // 🔑 Use the new detailed mapper
    }

    // 🔑 UPDATED: Return type is now List<AdmissionResponseDetailed>
    public List<AdmissionResponse> getStudentAdmissions(Long studentId) {
        // 1. Basic ID Validation
        validator.validateId(studentId, "Student");

        // Use the existing filter logic
        return admissionRepository.findAll().stream()
                .filter(a -> a.getStudent().getStudentId().equals(studentId))
                .map(this::mapToDetailedResponse) // 🔑 Use the new detailed mapper
                .collect(Collectors.toList());
    }

    // 🔑 NEW DETAILED MAPPER (Replaces mapToResponse)
    private AdmissionResponse mapToDetailedResponse(Admission admission) {
        AdmissionResponse response = new AdmissionResponse();
        response.setAdmissionId(admission.getAdmissionId());
        response.setAdmissionDate(admission.getAdmissionDate());
        response.setCompletionDate(admission.getCompletionDate());

        // 🔑 FIX: Map Timestamps
        response.setCreatedAt(admission.getCreatedAt());
        response.setUpdatedAt(admission.getUpdatedAt());

        // Map Nested Entities to Minimal DTOs
        response.setStudent(mapStudentToMinimal(admission.getStudent()));
        response.setInstitute(mapInstituteToMinimal(admission.getInstitute()));
        response.setCourse(mapCourseToMinimal(admission.getCourse()));

        return response;
    }

    // --- Minimal Mapper Helpers (These remain correct for nested DTOs) ---
    private StudentResponseMinimal mapStudentToMinimal(Student student) {
        StudentResponseMinimal minimal = new StudentResponseMinimal();
        minimal.setId(student.getStudentId());
        minimal.setFirstName(student.getFirstName());
        minimal.setLastName(student.getLastName());
        minimal.setEmail(student.getEmail());
        return minimal;
    }

    private InstituteResponseMinimal mapInstituteToMinimal(Institute institute) {
        InstituteResponseMinimal minimal = new InstituteResponseMinimal();
        minimal.setInstituteId(institute.getInstituteId());
        minimal.setInstituteName(institute.getInstituteName());
        return minimal;
    }

    private CourseResponseMinimal mapCourseToMinimal(Course course) {
        CourseResponseMinimal minimal = new CourseResponseMinimal();
        minimal.setCourseId(course.getCourseId());
        minimal.setCourseName(course.getCourseName());
        minimal.setDurationDays(course.getDurationDays());
        return minimal;
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