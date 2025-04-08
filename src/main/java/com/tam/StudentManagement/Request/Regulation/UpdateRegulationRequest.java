package com.tam.StudentManagement.Request.Regulation;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRegulationRequest {
    @Size(max = 30, message = "Code must not exceed 30 characters")
    private String code;

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    private String description;
    private String content;
    private Boolean isActive;
    private String dormitoryCode;
}