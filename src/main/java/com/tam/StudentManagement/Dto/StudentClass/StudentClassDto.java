package com.tam.StudentManagement.Dto.StudentClass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.StudentClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassDto {
    private Integer id;
    private String code;
    private String name;

    public StudentClassDto(StudentClass entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
    }
}