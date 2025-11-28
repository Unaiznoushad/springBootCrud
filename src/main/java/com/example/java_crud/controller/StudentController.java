package com.example.java_crud.controller;

import com.example.java_crud.dto.request.StudentRequest;
import com.example.java_crud.dto.response.ApiResponse;
import com.example.java_crud.dto.response.StudentResponseMinimal;
import com.example.java_crud.models.Student;
import com.example.java_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @PostMapping("/register")
//    public Student register(@RequestBody StudentRequest studentRequest){
//        return studentService.registerStudent(studentRequest);
//    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<StudentResponseMinimal>> register(@RequestBody StudentRequest request) {

        Student newStudentEntity = studentService.registerStudent(request);

        // Map the saved Entity to the Detailed DTO
        StudentResponseMinimal responseDto = studentService.mapToDetailedResponse(newStudentEntity);

        // Use the new ApiResponse factory method with the specific key "student"
        ApiResponse<StudentResponseMinimal> response = ApiResponse.success(
                "student",
                "Student successfully registered.",
                responseDto
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public String login(@RequestBody StudentRequest studentRequest){
        return studentService.login(studentRequest.getEmail(),studentRequest.getPassword());
    }
}
