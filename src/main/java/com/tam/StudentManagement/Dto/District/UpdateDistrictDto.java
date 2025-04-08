package com.tam.StudentManagement.Dto.District;

import lombok.Data;
import com.tam.StudentManagement.Model.District;

@Data
public class UpdateDistrictDto {
    private Integer id;
    private String code;
    private String name;
    private Integer type;
    private Integer provinceId;

    public UpdateDistrictDto(District entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
        this.provinceId = entity.getProvince().getId();
    }
}