package com.example.java_crud.models;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String houseName;
    private String street;
    private String post;
    private String city;
    private String state;
    private String pinCode;
}