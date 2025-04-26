package com.tam.StudentManagement.Dto.Student;

import lombok.Data;

@Data
public class StudentStatusDto {
    private int students;
    private int dormitories;
    private int confirmedStudents;
    private int unconfirmedStudents;


    public StudentStatusDto(int students, int dormitories, int confirmedStudents, int unconfirmedStudents) {
        this.students = students;
        this.dormitories = dormitories;
        this.confirmedStudents = confirmedStudents;
        this.unconfirmedStudents = unconfirmedStudents;
    }
}