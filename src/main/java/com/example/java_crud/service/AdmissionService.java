package com.example.java_crud.service;

import com.example.java_crud.models.Admission;
import com.example.java_crud.models.Course;
import com.example.java_crud.models.Institute;
import com.example.java_crud.models.Student;
import com.example.java_crud.repository.AdmissionRepository;
import com.example.java_crud.repository.CourseRepository;
import com.example.java_crud.repository.InstituteRepository;
import com.example.java_crud.repository.StudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Data
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    public Admission takeAdmission(Long studentId, Long instituteId, Long courseId) {

        Student student = studentRepository.findById(studentId).orElseThrow();
        Institute institute = instituteRepository.findById(instituteId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        Admission admission = new Admission();
        admission.setStudent(student);
        admission.setInstitute(institute);
        admission.setCourse(course);

        admission.setAdmission_date(new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, course.getDuration_days());
        admission.setCompletion_date(cal.getTime());

        return admissionRepository.save(admission);
    }

    public List<Admission> getStudentAdmissions(Long studentId) {
        return admissionRepository.findAll().stream()
                .filter(a -> a.getStudent().getStudent_id().equals(studentId))
                .toList();
    }

    public long countStudents(Long courseId) {
        return admissionRepository.findAll().stream()
                .filter(a -> a.getCourse().getCourse_id().equals(courseId))
                .count();
    }

    public long remainingDays(Long admissionId) {
        Admission admission = admissionRepository.findById(admissionId).orElseThrow();
        long diff = admission.getCompletion_date().getTime() - new Date().getTime();
        return diff / (1000 * 60 * 60 * 24);
    }
}

