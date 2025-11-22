package com.example.java_crud.service;

import com.example.java_crud.models.Student;
import com.example.java_crud.repository.StudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public String login(String email, String password) {

        Student s = studentRepository.findByEmail(email).orElse(null);

        if (s == null) {
            return "Email does not match";
        }

        if (!s.getPassword().equals(password)) {
            return "Incorrect password";
        }

        return "Login successful";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }
}
