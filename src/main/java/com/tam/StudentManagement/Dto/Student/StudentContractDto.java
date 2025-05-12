package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import java.time.LocalDate;

import com.tam.StudentManagement.Model.Dormitory;

@Data
public class StudentContractDto {
    private Integer dormitoryId;
    private String price;
    private String room;
    private Integer status;
}