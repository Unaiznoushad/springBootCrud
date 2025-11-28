package com.example.java_crud.dto.request;

import lombok.Data;

@Data
public class InstituteRequest {
    private String instituteName;
    private String address;
    private String contactNo;
    // Note: Your Entity didn't have email, so I removed it here too.
}