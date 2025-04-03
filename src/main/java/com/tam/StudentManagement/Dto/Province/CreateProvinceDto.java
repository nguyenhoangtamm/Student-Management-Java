package com.tam.StudentManagement.Dto.Province;

import lombok.Data;
import com.tam.StudentManagement.Model.Province;

@Data
public class CreateProvinceDto {
    private Integer id;
    private String code;
    private String name;
    private Integer type;

    public CreateProvinceDto(Province entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
    }
}