package com.tam.StudentManagement.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryServiceDto {
    private String name;
    private Double fee;
    private String unit;
}

