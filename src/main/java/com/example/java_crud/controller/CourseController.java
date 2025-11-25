package com.example.java_crud.controller;

import com.example.java_crud.models.Course;
import com.example.java_crud.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/byInstitute/{id}")
    public List<Course> getCourses(@PathVariable Long id) {
        return courseService.getCoursesByInstitute(id);
    }

    @PostMapping("/add")
    public Course add(@RequestBody Course course) {
        return courseService.addCourse(course);
    }
}

