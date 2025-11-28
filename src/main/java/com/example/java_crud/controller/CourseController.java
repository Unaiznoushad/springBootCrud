package com.example.java_crud.controller;

import com.example.java_crud.dto.request.CourseRequest;
import com.example.java_crud.dto.response.ApiResponse;
import com.example.java_crud.dto.response.CourseResponse;
import com.example.java_crud.dto.response.CourseResponseMinimal;
import com.example.java_crud.models.Course;
import com.example.java_crud.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/course")
//public class CourseController {
//
//    @Autowired
//    private CourseService courseService;
//
//    @GetMapping("/byInstitute/{id}")
//    public List<Course> getCourses(@PathVariable Long id) {
//        return courseService.getCoursesByInstitute(id);
//    }
//
//    @PostMapping("/add")
//    public Course add(@RequestBody Course course) {
//        return courseService.addCourse(course);
//    }
//}


@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    // Return type for single creation
    public ResponseEntity<ApiResponse<CourseResponseMinimal>> addCourse(@RequestBody CourseRequest request) {

        // Assuming service returns the saved Course entity
        Course courseEntity = courseService.addCourse(request);

        // Convert Entity to Detailed DTO
        CourseResponseMinimal responseDto = courseService.mapToDetailedResponse(courseEntity);

        // Wrap in ApiResponse
        ApiResponse<CourseResponseMinimal> response = ApiResponse.success(
                "course", // Key name in data payload
                "Course created successfully.",
                responseDto
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/byInstitute/{id}")
    // Change the return type to the List of DTOs
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getCoursesByInstitute(@PathVariable Long instituteId) {

        // 1. Get the list of DTOs from the service
        List<CourseResponse> courseList = courseService.getCoursesByInstitute(instituteId);

        // 2. Wrap the list in the standardized ApiResponse
        ApiResponse<List<CourseResponse>> response = ApiResponse.success(
                "courses", // Use 'courses' as the key for the list payload
                "Course list fetched successfully for institute ID " + instituteId,
                courseList
        );

        return ResponseEntity.ok(response);
    }

    // Keep getCourses similar, just map logic in Service
}
