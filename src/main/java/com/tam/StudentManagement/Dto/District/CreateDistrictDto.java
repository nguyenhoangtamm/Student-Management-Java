package com.tam.StudentManagement.Dto.District;

import lombok.Data;
import com.tam.StudentManagement.Model.District;

@Data
public class CreateDistrictDto {
    private Integer id;
    private String code;
    private String name;
    private Integer type;
    private Integer provinceId;

    public CreateDistrictDto(District entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
        this.provinceId = entity.getProvince().getId();
    }
}