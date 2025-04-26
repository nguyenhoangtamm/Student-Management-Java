package com.tam.StudentManagement.Dto.Student;

import lombok.Data;

@Data
public class StudentStatisticsDto {
    private int confirmedStudents;
    private int unconfirmedStudents;
    private int atHomeStudents;
    private int otherStudents;

    public StudentStatisticsDto( int confirmedStudents, int unconfirmedStudents, int atHomeStudents, int otherStudents) {
        this.confirmedStudents = confirmedStudents;
        this.unconfirmedStudents = unconfirmedStudents;
        this.atHomeStudents = atHomeStudents;
        this.otherStudents = otherStudents;
    }
}