package com.tam.StudentManagement.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDto {
    public String code;
    public String name;
    public String gender;
    public String avatar;
    public String status;
    public String className;
    public String level;
    public String faculty;
    public String major;
    public String educationType;
    public String course;
    public String phone;
    public String address;
    public String dateOfBirth;
    public String birthPlace;
    public String email;
    public String residenceStatus;
    public OffCampusDto offCampus;
}