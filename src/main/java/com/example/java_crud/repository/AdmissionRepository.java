package com.example.java_crud.repository;

import com.example.java_crud.models.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission,Long> {
}
