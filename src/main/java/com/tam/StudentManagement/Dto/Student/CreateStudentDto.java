package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import com.tam.StudentManagement.Model.Student;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    private Integer id;
    private String code;
    private String fullName;
    private Integer gender;
    private String dateOfBirth;
    private String faculty;
    private String room;
    private String phoneNumber;
    private String email;
    private Integer educationLevel;
    private Integer residenceStatus;
    private String academicYear;
    private String birthplace;
    private Integer status;
    private Boolean isAdmin;
    private String avatar;
    private BigDecimal monthlyRent;
    private Integer contractStatus;
    private String address;
    private String fullAddress;
    private Integer dormitoryId;
    private Integer classId;
    private Integer majorId;
    

    public CreateStudentDto(Student entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.fullName = entity.getFullName();
        this.gender = entity.getGender();
        this.dateOfBirth = entity.getDateOfBirth().toString();
        this.faculty = entity.getFaculty();
        this.room = entity.getRoom();
        this.phoneNumber = entity.getPhoneNumber();
        this.email = entity.getEmail();
        this.educationLevel = entity.getEducationLevel();
        this.dormitoryId = entity.getDormitoryId();
        this.classId = entity.getClassId();
        this.majorId = entity.getMajorId();
        this.residenceStatus = entity.getResidenceStatus();
        this.academicYear = entity.getAcademicYear();
        this.birthplace = entity.getBirthplace();
        this.status = entity.getStatus();
        this.isAdmin = entity.getIsAdmin();
        this.avatar = entity.getAvatar();
        this.monthlyRent = entity.getMonthlyRent();
        this.contractStatus = entity.getContractStatus();
        this.address = entity.getAddress();
        this.fullAddress = entity.getFullAddress();
        this.dormitoryId = entity.getDormitoryId();
        this.classId = entity.getClassId();
        this.majorId = entity.getMajorId();
    }

}
