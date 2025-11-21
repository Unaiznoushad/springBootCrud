package com.example.java_crud.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "institute")
@Data
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long institute_id;

    private String institute_name;
    private String address;
    private String contact_no;
}
