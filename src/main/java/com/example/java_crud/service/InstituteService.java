//package com.example.java_crud.service;
//
//import com.example.java_crud.models.Institute;
//import com.example.java_crud.repository.InstituteRepository;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//@Data
//public class InstituteService {
//    @Autowired
//    private InstituteRepository instituteRepository;
//
//    public List<Institute> getAllInstitutes() {
//        return instituteRepository.findAll();
//    }
//
//    public Institute addInstitute(Institute institute) {
//        return instituteRepository.save(institute);
//    }
//}

package com.example.java_crud.service;

import com.example.java_crud.dto.request.InstituteRequest;
import com.example.java_crud.dto.response.InstituteResponse;
import com.example.java_crud.dto.response.InstituteResponseMinimal;
import com.example.java_crud.models.Institute;
import com.example.java_crud.repository.InstituteRepository;
import com.example.java_crud.validation.AppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private AppValidator validator;

    // Get All - Return List of Responses, not Entities
    public List<InstituteResponseMinimal> getAllInstitutes() {
        return instituteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Add - Validate -> Map to Entity -> Save -> Map to Response
    public InstituteResponseMinimal addInstitute(InstituteRequest request) {
        // 1. Validation
        validator.validateInstitute(request);

        // 2. Map DTO to Entity
        Institute institute = new Institute();
        institute.setInstituteName(request.getInstituteName());
        institute.setAddress(request.getAddress());
        institute.setContactNo(request.getContactNo());
        institute.setCreatedAt(LocalDateTime.now());
        institute.setUpdatedAt(LocalDateTime.now());

        // 3. Save and return Entity
        Institute savedInstitute = instituteRepository.save(institute);

        // 4. Return Detailed DTO
        return mapToResponse(savedInstitute);
    }

    // Helper method to keep code clean
    public InstituteResponseMinimal mapToResponse(Institute institute) {
        InstituteResponseMinimal dto = new InstituteResponseMinimal();

        // Ensure you map the ID field correctly (assuming the DTO field is 'id')
        dto.setInstituteId(institute.getInstituteId());
        dto.setInstituteName(institute.getInstituteName());
        dto.setAddress(institute.getAddress());
        dto.setContactNo(institute.getContactNo());
        dto.setCreatedAt(institute.getCreatedAt());
        dto.setUpdatedAt(institute.getUpdatedAt());

        return dto;
    }
}