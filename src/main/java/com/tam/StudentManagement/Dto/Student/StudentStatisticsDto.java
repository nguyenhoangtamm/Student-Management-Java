package com.tam.StudentManagement.Dto.Student;

import lombok.Data;

@Data
public class StudentStatisticsDto {
    private Integer totalStudents;
    private Integer activeStudents;
    private Integer inactiveStudents;
    private Integer offCampusStudents;
    private Integer onCampusStudents;
    private Integer maleStudents;
    private Integer femaleStudents;
    private Integer internationalStudents;
    private Integer domesticStudents;
}