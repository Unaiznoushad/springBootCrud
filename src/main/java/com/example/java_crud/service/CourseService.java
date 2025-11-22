package com.example.java_crud.service;

import com.example.java_crud.models.Course;
import com.example.java_crud.models.Institute;
import com.example.java_crud.repository.CourseRepository;
import com.example.java_crud.repository.InstituteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    public List<Course> getCoursesByInstitute(Long instituteId) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getInstitute().getInstituteId().equals(instituteId))
                .toList();
    }

    public Course addCourse(Course course) {


        Long instituteId = course.getInstitute().getInstituteId();

        Institute institute = instituteRepository.findById(instituteId)
                .orElseThrow(() -> new RuntimeException("Institute not found"));

        // 3. set the full object back into course
        course.setInstitute(institute);

        // 4. save course
        return courseRepository.save(course);
    }

}

