//package com.example.java_crud.controller;
//
//import com.example.java_crud.models.Institute;
//import com.example.java_crud.service.InstituteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/institute")
//public class InstituteController {
//
//    @Autowired
//    private InstituteService instituteService;
//
//    @GetMapping("/list")
//    public List<Institute> list() {
//        return instituteService.getAllInstitutes();
//    }
//
//    @PostMapping("/add")
//    public Institute add(@RequestBody Institute institute) {
//        return instituteService.addInstitute(institute);
//    }
//}

package com.example.java_crud.controller;

import com.example.java_crud.dto.request.InstituteRequest;
import com.example.java_crud.dto.response.ApiResponse;
import com.example.java_crud.dto.response.InstituteResponse;
import com.example.java_crud.dto.response.InstituteResponseMinimal;
import com.example.java_crud.models.Institute;
import com.example.java_crud.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institute")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @GetMapping("/list")
    // 🔑 FIX: Wrap the list of DTOs in ResponseEntity<ApiResponse<List<...>>>
    public ResponseEntity<ApiResponse<List<InstituteResponseMinimal>>> list() {

        // 1. Get the list of DTOs from the service
        List<InstituteResponseMinimal> instituteList = instituteService.getAllInstitutes();

        // 2. Wrap the list in the standardized ApiResponse
        ApiResponse<List<InstituteResponseMinimal>> response = ApiResponse.success(
                "institutes", // Use 'institutes' as the descriptive key name
                "Institute list fetched successfully.",
                instituteList
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    // Return type for single creation
    public ResponseEntity<ApiResponse<InstituteResponseMinimal>> addInstitute(@RequestBody InstituteRequest request) {

        // Assuming service returns the saved Institute entity
        InstituteResponseMinimal responseDto = instituteService.addInstitute(request);

        // Convert Entity to Detailed DTO
//        InstituteResponseMinimal responseDto = instituteService.mapToResponse(instituteEntity);

        // Wrap in ApiResponse
        ApiResponse<InstituteResponseMinimal> response = ApiResponse.success(
                "institute", // Key name in data payload
                "Institute created successfully.",
                responseDto
        );

        return ResponseEntity.ok(response);
    }
}