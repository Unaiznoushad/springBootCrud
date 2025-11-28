package com.example.java_crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String status;
    private String message;

    // T is the Entity/DTO (e.g., Student), but it's wrapped in a Map
    // to include the key ("student", "course", etc.)
    private Map<String, T> data;

    // --- Success Factory Method ---
    public static <T> ApiResponse<T> success(String key, String message, T data) {
        return ApiResponse.<T>builder()
                .status("SUCCESS")
                .message(message)
                .data(Map.of(key, data)) // Creates the {"student": T} structure
                .build();
    }

    // --- Error Factory Method (Returns null for data on error, as requested) ---
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus("ERROR");
        response.setMessage(message);
        response.setData(null); // Or however you handle error data
        return response;
    }
}