package com.example.java_crud.controller;

import com.example.java_crud.models.Student;
import com.example.java_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public Student register(@RequestBody Student student){
        return studentService.registerStudent(student);
    }

    @PostMapping("/login")
    public String login(@RequestBody Student student){
        return studentService.login(student.getEmail(),student.getPassword());
    }
}
