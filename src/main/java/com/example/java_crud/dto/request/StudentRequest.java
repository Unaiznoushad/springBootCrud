//package com.example.java_crud.dto.request;
//
//import lombok.Data;
//
//@Data
//public class StudentRequest {
//    private String name;
//    private String email;
//    private String password;
//    private String phone;
//}

package com.example.java_crud.dto.request;

// ... imports ...
import com.example.java_crud.models.Address;
import com.example.java_crud.models.ClassInfo;
import lombok.Data;
@Data
public class StudentRequest {
    // New fields replacing the single 'name' and 'phone' fields
    private String firstName;
    private String lastName;
    private String phoneNumber; // Renamed

    // Existing fields
    private String email;
    private String password;
    private String dateOfBirth;

    private String gender;
    private Address address;
    private ClassInfo classInfo;
    // ... add any other new fields required for registration (dateOfBirth, gender, address fields)
}