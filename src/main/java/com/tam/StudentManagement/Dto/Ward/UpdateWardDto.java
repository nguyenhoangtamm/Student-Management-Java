package com.tam.StudentManagement.Dto.Ward;

import lombok.Data;

@Data
public class UpdateWardDto {
    private String code;
    private String name;
    private Integer type;
    private Integer districtId;
}