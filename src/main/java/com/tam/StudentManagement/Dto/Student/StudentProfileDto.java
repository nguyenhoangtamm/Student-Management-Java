package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentProfileDto {
    private String studentId;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;
    private String className;
    private String major;
    private String status;
    private String avatar;
    private String idCard;
    private LocalDate idCardIssueDate;
    private String idCardIssuePlace;
}