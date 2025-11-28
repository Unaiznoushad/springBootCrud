package com.example.java_crud.config;

import com.example.java_crud.models.Student;
import com.example.java_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired StudentRepository studentRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (studentRepository.findByEmail("admin@school.com").isEmpty()) {
            Student admin = new Student();
            admin.setFirstName("Super");
            admin.setLastName("Admin");
            admin.setEmail("admin@school.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // Hash the password
            admin.setRole("ROLE_ADMIN");
            admin.setPhoneNumber("0000000000");
            studentRepository.save(admin);
            System.out.println("SUPER ADMIN CREATED: admin@school.com / admin123");
        }
    }
}