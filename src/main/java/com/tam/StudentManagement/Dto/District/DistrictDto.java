package com.tam.StudentManagement.Dto.District;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.District;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {
    private Integer id;
    private String code;
    private String name;
    private Integer type;
    private Integer provinceId;

    public DistrictDto(District entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
        this.provinceId = entity.getProvince().getId();
    }
}