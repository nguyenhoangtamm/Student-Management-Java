package com.tam.StudentManagement.Request.Regulation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data 
public class CreateRegulationRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "URL must not exceed 255 characters")
    private String url;

    @NotNull(message = "Type is required")
    private Integer type;

    @NotNull(message = "Effective date is required")
    private LocalDate effectiveDate;

    @NotNull(message = "Active status is required")
    private Boolean isActive;
    


}