package com.example.java_crud.dto.response;

import lombok.Data;

@Data
public class InstituteResponse {
    private Long instituteId;
    private String instituteName;
    private String address;
    private String contactNo;
}