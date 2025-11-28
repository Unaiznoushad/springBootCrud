//package com.example.java_crud.service;
//
//import com.example.java_crud.models.Student;
//import com.example.java_crud.repository.StudentRepository;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Data
//public class StudentService {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    public Student registerStudent(Student student) {
//        return studentRepository.save(student);
//    }
//
//    public String login(String email, String password) {
//
//        Student s = studentRepository.findByEmail(email).orElse(null);
//
//        if (s == null) {
//            return "Email does not match";
//        }
//
//        if (!s.getPassword().equals(password)) {
//            return "Incorrect password";
//        }
//
//        return "Login successful";
//    }
//}
//

package com.example.java_crud.service;

import com.example.java_crud.dto.request.StudentRequest;
import com.example.java_crud.dto.response.StudentResponseMinimal;
import com.example.java_crud.models.Student;
import com.example.java_crud.repository.StudentRepository;
import com.example.java_crud.validation.AppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AppValidator validator; // Inject Validator

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student registerStudent(StudentRequest request) {
        // 1. Validate
        validator.validateStudent(request);

        // 2. Map DTO to Entity
        Student student = new Student();
//        student.setStudentSystemId("STU" + (studentRepository.count() + 1000));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setRole("ROLE_STUDENT");
        try {
            // Convert the String date from the request DTO to LocalDate for the Entity
            if (request.getDateOfBirth() != null && !request.getDateOfBirth().trim().isEmpty()) {
                LocalDate dob = LocalDate.parse(request.getDateOfBirth());
                student.setDateOfBirth(dob);
            }
        } catch (DateTimeParseException e) {
            // If the date format is wrong (e.g., '1/1/2000' instead of '2000-01-01')
            throw new IllegalArgumentException("Invalid date format for Date of Birth. Use YYYY-MM-DD.");
        }
        student.setGender(request.getGender());
        student.setAddress(request.getAddress());
        student.setClassInfo(request.getClassInfo());
        student.setUpdatedAt(LocalDateTime.now());
        student.setStatus("ACTIVE");
        // 3. Save
        return studentRepository.save(student);
    }
    public StudentResponseMinimal mapToDetailedResponse(Student student) {
        StudentResponseMinimal dto = new StudentResponseMinimal();
        dto.setId(student.getStudentId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setGender(student.getGender());
        dto.setAddress(student.getAddress());
        dto.setClassInfo(student.getClassInfo());
        dto.setStatus(student.getStatus());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());
        return dto;
    }

    // Login remains mostly the same, or you can move login validation to AppValidator too.
    public String login(String email, String password) {

        // 1. INPUT VALIDATION (using AppValidator)
        validator.validateLoginCredentials(email, password);

        // 2. BUSINESS LOGIC (database lookup)
        Student s = studentRepository.findByEmail(email).orElse(null);

        // 3. CREDENTIAL VERIFICATION
        if (s == null) {
            // Throw an exception so the GlobalExceptionHandler catches it and returns 400
            throw new IllegalArgumentException("Email does not match any registered student.");
        }

        // Note: In a real app, you would use a password encoder (like BCrypt) here.
        if (!s.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password.");
        }

        return "Login successful";
    }
}
