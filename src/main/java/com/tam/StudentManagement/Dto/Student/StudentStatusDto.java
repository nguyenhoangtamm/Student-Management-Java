package com.tam.StudentManagement.Dto.Student;

import lombok.Data;

@Data
public class StudentStatusDto {
    private String studentId;
    private String fullName;
    private String status;
    private String currentSemester;
    private String academicStatus;
    private String enrollmentStatus;
    private Boolean isOffCampus;
    private String offCampusReason;
    private String offCampusAddress;
}