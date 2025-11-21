package com.example.java_crud.controller;

import com.example.java_crud.models.Admission;
import com.example.java_crud.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @PostMapping("/take")
    public Admission take(@RequestParam Long studentId,
                          @RequestParam Long instituteId,
                          @RequestParam Long courseId) {
        return admissionService.takeAdmission(studentId, instituteId, courseId);
    }

    @GetMapping("/student/{id}")
    public List<Admission> studentAdmissions(@PathVariable Long id) {
        return admissionService.getStudentAdmissions(id);
    }

    @GetMapping("/count/{courseId}")
    public long count(@PathVariable Long courseId) {
        return admissionService.countStudents(courseId);
    }

    @GetMapping("/remaining/{admissionId}")
    public long remaining(@PathVariable Long admissionId) {
        return admissionService.remainingDays(admissionId);
    }
}

