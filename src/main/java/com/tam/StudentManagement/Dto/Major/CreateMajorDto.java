package com.tam.StudentManagement.Dto.Major;

import com.tam.StudentManagement.Model.Major;

import lombok.Data;

@Data
public class CreateMajorDto {
    private Integer id;
    private String code;
    private String name;
    private Integer totalStudent;

    public CreateMajorDto(Major entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.totalStudent = entity.getTotalStudent();
    }
}