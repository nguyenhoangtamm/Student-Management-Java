package com.tam.StudentManagement.Dto.Dormitory;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateDormitoryDto {
    private String name;
    private String address;
    private String fullAddress;
    private Integer wardId;
    private Integer districtId;
    private Integer provinceId;
    private String ownerName;
    private String phoneNumber;
    private String description;
    private String content;
    private Integer rooms;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Float longitude;
    private Float latitude;
}