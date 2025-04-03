package com.tam.StudentManagement.Dto.Ward;

import lombok.Data;
import com.tam.StudentManagement.Model.Ward;

@Data
public class CreateWardDto {
    private Integer id;
    private String code;
    private String name;
    private Integer type;
    private Integer districtId;

    public CreateWardDto(Ward entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
        this.districtId = entity.getDistrict().getId();
    }
} 