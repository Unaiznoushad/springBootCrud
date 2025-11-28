package com.example.java_crud.validation;

import com.example.java_crud.dto.request.CourseRequest;
import com.example.java_crud.dto.request.InstituteRequest;
import com.example.java_crud.dto.request.StudentRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AppValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // ... (validateStudent remains the same)

    public void validateInstitute(InstituteRequest request) {
        if (request.getInstituteName() == null || request.getInstituteName().trim().isEmpty()) {
            throw new IllegalArgumentException("Institute Name is mandatory");
        }
        if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Address is mandatory");
        }
        if (request.getContactNo() == null || request.getContactNo().length() != 10) {
            throw new IllegalArgumentException("Contact Number must be 10 digits");
        }
    }

    // ... (validateCourse remains the same)
    public void validateCourse(CourseRequest request) {
        if (request.getCourseName() == null || request.getCourseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Course Name is mandatory");
        }
        if (request.getDurationDays() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0 days");
        }
        if (request.getInstituteId() == null) {
            throw new IllegalArgumentException("Institute ID is required");
        }
    }

    // ... (Add validateStudent here from previous step if needed)
    public void validateStudent(StudentRequest request) {
        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }

        // 🔑 FIX 2: Validate lastName
        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required");
        }
        if (request.getEmail() == null || !Pattern.matches(EMAIL_REGEX, request.getEmail())) {
            throw new IllegalArgumentException("Invalid or missing Email");
        }
        if (request.getPhoneNumber() == null || request.getPhoneNumber().length() != 10) {
            throw new IllegalArgumentException("Phone Number must be 10 digits");
            // add other student checks...
        }
    }

    // ... existing code

    public void validateLoginCredentials(String email, String password) {
        // Check Email Format
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Check Password Presence (you might add length checks here too)
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }

    public void validateId(Long id, String resourceName) {
        if (id == null) {
            throw new IllegalArgumentException(resourceName + " ID cannot be null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException(resourceName + " ID must be a positive number.");
        }
    }
// ... existing code
}