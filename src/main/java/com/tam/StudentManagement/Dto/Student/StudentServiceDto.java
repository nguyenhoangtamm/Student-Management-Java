package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentServiceDto {
    private String name;
    private Integer price;
    private String unit;
}
