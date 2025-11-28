//package com.example.java_crud.service;
//
//import com.example.java_crud.models.Course;
//import com.example.java_crud.models.Institute;
//import com.example.java_crud.repository.CourseRepository;
//import com.example.java_crud.repository.InstituteRepository;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Data
//public class CourseService {
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private InstituteRepository instituteRepository;
//
//    public List<Course> getCoursesByInstitute(Long instituteId) {
//        return courseRepository.findAll().stream()
//                .filter(c -> c.getInstitute().getInstituteId().equals(instituteId))
//                .toList();
//    }
//
//    public Course addCourse(Course course) {
//
//
//        Long instituteId = course.getInstitute().getInstituteId();
//
//        Institute institute = instituteRepository.findById(instituteId)
//                .orElseThrow(() -> new RuntimeException("Institute not found"));
//
//        // 3. set the full object back into course
//        course.setInstitute(institute);
//
//        // 4. save course
//        return courseRepository.save(course);
//    }
//}


package com.example.java_crud.service;

import com.example.java_crud.dto.request.CourseRequest;
import com.example.java_crud.dto.response.CourseResponse;
import com.example.java_crud.dto.response.CourseResponseMinimal;
import com.example.java_crud.models.Course;
import com.example.java_crud.models.Institute;
import com.example.java_crud.repository.CourseRepository;
import com.example.java_crud.repository.InstituteRepository;
import com.example.java_crud.validation.AppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired private CourseRepository courseRepository;
    @Autowired private InstituteRepository instituteRepository;
    @Autowired private AppValidator validator;

    public Course addCourse(CourseRequest request) {
        // 1. Validation
        validator.validateCourse(request);

        // 2. Fetch Institute
        Institute institute = instituteRepository.findById(request.getInstituteId())
                .orElseThrow(() -> new IllegalArgumentException("Institute not found with ID: " + request.getInstituteId()));

        // 3. Map DTO to Entity
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDurationDays(request.getDurationDays());
        course.setInstitute(institute); // Set the relationship
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());

        // 4. Save and return Entity
        return courseRepository.save(course);
    }

    // 🔑 Mapping Method: Entity to Detailed DTO
    public CourseResponseMinimal mapToDetailedResponse(Course course) {
        CourseResponseMinimal dto = new CourseResponseMinimal();

        dto.setCourseId(course.getCourseId()); // Assuming DTO field is 'id'
        dto.setCourseName(course.getCourseName());
        dto.setDurationDays(course.getDurationDays());

        // Map the associated institute ID
        if (course.getInstitute() != null) {
            dto.setInstituteName(course.getInstitute().getInstituteName());
        }

        dto.setCreatedAt(course.getCreatedAt());
        dto.setUpdatedAt(course.getUpdatedAt());

        return dto;
    }

    public List<CourseResponse> getCoursesByInstitute(Long instituteId) {

        // 1. VALIDATION: Check the input ID before proceeding
        validator.validateId(instituteId, "Institute");

        // 2. BUSINESS LOGIC: Fetch the entities
        List<Course> courses = courseRepository.findAll().stream()
                .filter(c -> c.getInstitute().getInstituteId().equals(instituteId))
                .toList();

        // 3. MAPPING: Convert Entities to Response DTOs
        return courses.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Helper method to map Entity to Response DTO
    private CourseResponse mapToResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setCourseId(course.getCourseId());
        response.setCourseName(course.getCourseName());
        response.setDurationDays(course.getDurationDays());

        // Ensure you handle the nested object safely
        if (course.getInstitute() != null) {
            response.setInstituteName(course.getInstitute().getInstituteName());
        }
        return response;
    }
}
