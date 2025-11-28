//package com.example.java_crud.dto.response;
//
//import lombok.Data;
//
//@Data
//public class StudentResponseMinimal {
//    private Long studentId;
//    private String name;
//    private String email;
//    // Removed: password, createdAt
//}

package com.example.java_crud.dto.response;

import com.example.java_crud.models.Address;
import com.example.java_crud.models.ClassInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({
        "_class",
        "id",
        "firstName",
        "lastName",
        "email",
        "phoneNumber",
        "dateOfBirth",
        "gender",
        "address",
        "classInfo",
        "status",
        "createdAt",
        "updatedAt"
})
public class StudentResponseMinimal {

    @JsonProperty("_class")
    private final String classType = "com.example.java_crud.models.Student";

    private Long id; // Maps to studentSystemId
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;

    private Address address; // Since Address is @Embeddable, we can include the model directly
    private ClassInfo classInfo;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}