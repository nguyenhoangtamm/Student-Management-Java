package com.tam.StudentManagement.Dto.Province;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.Province;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDto {
    private Integer id;
    private String code;
    private String name;
    private Integer type;

    public ProvinceDto(Province entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
    }
}