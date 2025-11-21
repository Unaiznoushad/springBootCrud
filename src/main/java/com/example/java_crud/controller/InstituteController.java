package com.example.java_crud.controller;

import com.example.java_crud.models.Institute;
import com.example.java_crud.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institute")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @GetMapping("/list")
    public List<Institute> list() {
        return instituteService.getAllInstitutes();
    }

    @PostMapping("/add")
    public Institute add(@RequestBody Institute institute) {
        return instituteService.addInstitute(institute);
    }
}
