package com.example.java_crud.controller;

import com.example.java_crud.dto.request.StudentRequest;
import com.example.java_crud.dto.response.ApiResponse; // 🔑 Import the ApiResponse wrapper
import com.example.java_crud.dto.response.LoginResponse;
import com.example.java_crud.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // 🔑 Import ResponseEntity
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    // 🔑 Change return type to ResponseEntity wrapping ApiResponse<String>
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody StudentRequest authRequest) {

        // 1. Attempt Authentication
        // This process handles invalid credentials by throwing an AuthenticationException
        // which your GlobalExceptionHandler should ideally catch and wrap as an ERROR response.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {

            // 2. Generate Token
            String token = jwtUtil.generateToken(authRequest.getEmail());
            LoginResponse loginResponseDto = new LoginResponse(token);

            // 3. Wrap the token in the standard ApiResponse structure
            ApiResponse<LoginResponse> response = ApiResponse.success(
                    "token", // The key for the data payload
                    "Authentication successful. Token generated.",
                    loginResponseDto
            );

            // Return 200 OK with the structured response
            return ResponseEntity.ok(response);

        } else {
            // This path is technically redundant if authenticationManager throws exceptions,
            // but we keep it for safety based on your original logic.
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}