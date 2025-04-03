package com.tam.StudentManagement.Dto.StudentClass;

import lombok.Data;
import com.tam.StudentManagement.Model.StudentClass;

@Data
public class CreateStudentClassDto {
    private Integer id;
    private String code;
    private String fullName;
    private Integer totalStudent;

    public CreateStudentClassDto(StudentClass entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.fullName = entity.getFullName();
        this.totalStudent = entity.getTotalStudent();
    }
}