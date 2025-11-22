package com.example.java_crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "institute")
public class Institute {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instituteId;

    private String instituteName;
    private String address;
    private String contactNo;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses;

    public Long getInstituteId() { return instituteId; }
    public void setInstituteId(Long instituteId) { this.instituteId = instituteId; }
    public String getInstituteName() { return instituteName; }
    public void setInstituteName(String instituteName) { this.instituteName = instituteName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
