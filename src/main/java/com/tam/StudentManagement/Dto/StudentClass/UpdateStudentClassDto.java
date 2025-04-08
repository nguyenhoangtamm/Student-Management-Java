package com.tam.StudentManagement.Dto.StudentClass;

import com.tam.StudentManagement.Model.StudentClass;

import lombok.Data;

@Data
public class UpdateStudentClassDto {
    private Integer id;
    private String code;
    private String fullName;
    private Integer totalStudent;

    public UpdateStudentClassDto(StudentClass entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.fullName = entity.getFullName();
        this.totalStudent = entity.getTotalStudent();
    }
}