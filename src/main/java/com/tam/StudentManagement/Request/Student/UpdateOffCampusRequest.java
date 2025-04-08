package com.tam.StudentManagement.Request.Student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOffCampusRequest {
    @NotNull
    private Boolean isOffCampus;

    private String reason;

    private String address;

    private String contactNumber;

    private String emergencyContact;
}