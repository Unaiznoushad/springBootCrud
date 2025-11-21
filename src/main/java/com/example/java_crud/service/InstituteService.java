package com.example.java_crud.service;

import com.example.java_crud.models.Institute;
import com.example.java_crud.repository.InstituteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class InstituteService {
    @Autowired
    private InstituteRepository instituteRepository;

    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    public Institute addInstitute(Institute institute) {
        return instituteRepository.save(institute);
    }
}
