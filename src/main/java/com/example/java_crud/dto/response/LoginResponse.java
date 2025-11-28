package com.example.java_crud.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "_class", "token", "createdAt", "updatedAt" })
public class LoginResponse {

    // 🔑 Required metadata field
    @JsonProperty("_class")
    private final String classType = "com.example.java_crud.models.Student";
    // Note: We use 'AuthToken' as the conceptual model class for the token payload.

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Optional constructor for convenience
    public LoginResponse(String token) {
        this.token = token;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}