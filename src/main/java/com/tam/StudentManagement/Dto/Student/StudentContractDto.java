package com.tam.StudentManagement.Dto.Student;

import lombok.Data;


@Data
public class StudentContractDto {
    private Integer dormitoryId;
    private String price;
    private String room;
    private Integer status;
}