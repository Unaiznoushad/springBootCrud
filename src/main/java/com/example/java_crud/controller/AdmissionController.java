package com.example.java_crud.controller;

import com.example.java_crud.dto.response.AdmissionResponse;
import com.example.java_crud.dto.response.ApiResponse;
import com.example.java_crud.models.Admission;
import com.example.java_crud.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @PostMapping("/take")
    public ResponseEntity<ApiResponse<AdmissionResponse>> takeAdmission(
            @RequestParam Long studentId,
            @RequestParam Long instituteId,
            @RequestParam Long courseId)
    {
        // Assuming service returns the AdmissionResponseDetailed DTO
        AdmissionResponse responseDto = admissionService.takeAdmission(studentId, instituteId, courseId);

        // Wrap in ApiResponse
        ApiResponse<AdmissionResponse> response = ApiResponse.success(
                "admission", // Key name in data payload
                "Admission recorded successfully.",
                responseDto
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<ApiResponse<List<AdmissionResponse>>> studentAdmissions(@PathVariable Long id) {

        // 1. Get the list of DTOs from the service
        // The service layer must return List<AdmissionResponseDetailed>
        List<AdmissionResponse> admissionList = admissionService.getStudentAdmissions(id);

        // 2. Wrap the list in the standardized ApiResponse
        ApiResponse<List<AdmissionResponse>> response = ApiResponse.success(
                "admissions", // 🔑 Key name for the list payload
                "Admission list fetched successfully for student ID " + id,
                admissionList
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/count/{courseId}")
    public ResponseEntity<ApiResponse<Long>> countStudents(@PathVariable Long courseId) {

        // 1. Get the count from the service
        long studentCount = admissionService.countStudents(courseId);

        // 2. Wrap the result in the standardized ApiResponse
        ApiResponse<Long> response = ApiResponse.success(
                "studentCount", // Key name for the data payload
                "Student count retrieved successfully for course ID " + courseId,
                studentCount
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/remaining/{id}")
    public ResponseEntity<ApiResponse<String>> remainingDays(@PathVariable Long admissionId) {

        // 1. Get the formatted result from the service
        String resultString = admissionService.remainingDays(admissionId);

        // Check if the service returned an error message (like "Admission ID not found")
        if (resultString.contains("not found")) {
            ApiResponse<String> errorResponse = ApiResponse.error(resultString);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // 2. Wrap the result in the standardized ApiResponse
        ApiResponse<String> response = ApiResponse.success(
                "remainingDays", // Key name for the data payload
                "Remaining course duration retrieved successfully.",
                resultString
        );

        return ResponseEntity.ok(response);
    }

}

