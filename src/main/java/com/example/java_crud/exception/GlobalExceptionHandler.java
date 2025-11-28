//package com.example.java_crud.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    /**
//     * Handles IllegalArgumentException, which is thrown by AppValidator for mandatory field checks
//     * and data format errors. Converts the exception into a 400 Bad Request response.
//     */
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Object> handleIllegalArgumentException(
//            IllegalArgumentException ex,
//            WebRequest request)
//    {
//        // 1. Prepare the custom error body
//        Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.put("timestamp", new java.util.Date());
//        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
//        errorDetails.put("error", "Validation Error");
//        errorDetails.put("message", ex.getMessage()); // This carries "Name required", "Invalid Email", etc.
//        errorDetails.put("path", request.getDescription(false).replace("uri=", ""));
//
//        // 2. Return the response with 400 status
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * Handles a general RuntimeException (e.g., if a related entity like Institute is not found).
//     * This often results in a 404 or 400 depending on the scenario.
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
//        Map<String, Object> errorDetails = new HashMap<>();
//        // Note: You might want to define a specific custom exception (e.g., ResourceNotFoundException)
//        // for better status mapping, but this covers general runtime errors for now.
//        errorDetails.put("timestamp", new java.util.Date());
//        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
//        errorDetails.put("error", "Processing Error");
//        errorDetails.put("message", ex.getMessage());
//        errorDetails.put("path", request.getDescription(false).replace("uri=", ""));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }
//
//    // You can add more @ExceptionHandler methods here for other specific exceptions (e.g., NullPointerException)
//}

package com.example.java_crud.exception;

import com.example.java_crud.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(
            IllegalArgumentException ex,
            WebRequest request)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Use the error factory method
        ApiResponse<?> response = ApiResponse.error("Validation Error: " + ex.getMessage());

        // Return the 400 status with the error body
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex, WebRequest request) {

        // For general processing errors, use 500 (Internal Server Error)
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiResponse<?> response = ApiResponse.error("Server Error: " + ex.getMessage());

        return new ResponseEntity<>(response, status);
    }

    // Add handling for Authentication exceptions if needed later
}