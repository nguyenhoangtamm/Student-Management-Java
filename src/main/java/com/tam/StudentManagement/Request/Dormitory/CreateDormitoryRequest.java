package com.tam.StudentManagement.Request.Dormitory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateDormitoryRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotBlank(message = "Full address is required")
    private String fullAddress;

    @NotNull(message = "Ward ID is required")
    private Integer wardId;

    @NotNull(message = "District ID is required")
    private Integer districtId;

    @NotNull(message = "Province ID is required")
    private Integer provinceId;

    @NotBlank(message = "Owner name is required")
    @Size(max = 50, message = "Owner name must not exceed 50 characters")
    private String ownerName;

    @NotBlank(message = "Phone number is required")
    @Size(max = 30, message = "Phone number must not exceed 30 characters")
    private String phoneNumber;

    private String description;
    private String content;

    @NotNull(message = "Number of rooms is required")
    private Integer rooms;

    @NotNull(message = "Minimum price is required")
    private BigDecimal minPrice;

    @NotNull(message = "Maximum price is required")
    private BigDecimal maxPrice;

    private Float longitude;
    private Float latitude;
}