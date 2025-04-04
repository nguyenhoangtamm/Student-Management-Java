package com.tam.StudentManagement.Request.Major;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMajorRequest {
    @Size(max = 30, message = "Code must not exceed 30 characters")
    private String code;

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;
}