package com.tam.StudentManagement.Request.Ward;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateWardRequest {
    @Size(max = 30, message = "Code must not exceed 30 characters")
    private String code;

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    private Integer type;
    private Integer districtId;
}