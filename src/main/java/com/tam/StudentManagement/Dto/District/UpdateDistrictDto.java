package com.tam.StudentManagement.Dto.District;

import lombok.Data;

@Data
public class UpdateDistrictDto {
    private String code;
    private String name;
    private Integer type;
    private Integer provinceId;
}