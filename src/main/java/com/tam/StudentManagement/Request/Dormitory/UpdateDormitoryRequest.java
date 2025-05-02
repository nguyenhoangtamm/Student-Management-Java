package com.tam.StudentManagement.Request.Dormitory;

import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateDormitoryRequest {
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    private Integer wardId;
    private Integer districtId;
    private Integer provinceId;

    @Size(max = 50, message = "Owner name must not exceed 50 characters")
    private String ownerName;

    @Size(max = 30, message = "Phone number must not exceed 30 characters")
    private String phoneNumber;

    private String description;
    private String content;
    private Integer rooms;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Float longitude;
    private Float latitude;
}