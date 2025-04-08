package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentContractDto {
    private String contractId;
    private String studentId;
    private String fullName;
    private String contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String content;
    private String signature;
}