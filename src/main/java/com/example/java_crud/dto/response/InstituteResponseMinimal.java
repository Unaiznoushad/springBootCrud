package com.example.java_crud.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "_class", "instituteId", "instituteName", "address", "contactNo", "createdAt", "updatedAt" })
public class InstituteResponseMinimal {

    @JsonProperty("_class")
    private final String classType = "com.example.java_crud.models.Institute";

    private Long instituteId; // Maps to instituteId
    private String instituteName;
    private String address;
    private String contactNo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}