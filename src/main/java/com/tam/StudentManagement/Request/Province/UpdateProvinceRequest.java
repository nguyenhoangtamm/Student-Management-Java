package com.tam.StudentManagement.Request.Province;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProvinceRequest {
    @Size(max = 30, message = "Code must not exceed 30 characters")
    private String code;

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    private Integer type;
}