package com.tam.StudentManagement.Dto.Student;

import com.tam.StudentManagement.Model.Student;
import lombok.Getter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Integer id;
    private String code;
    private String fullName;
    private Integer gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private Integer classId;
    private Integer majorId;
    private Integer residenceStatus;
    private String academicYear;

    // Constructor chuyển đổi từ entity sang DTO
    public StudentDto(Student entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.fullName = entity.getFullName();
        this.gender = entity.getGender();
        this.dateOfBirth = entity.getDateOfBirth();
        this.email = entity.getEmail();
        this.phone = entity.getPhoneNumber();
        this.provinceId = entity.getProvince() != null ? entity.getProvince().getId() : null;
        this.districtId = entity.getDistrict() != null ? entity.getDistrict().getId() : null;
        this.wardId = entity.getWard() != null ? entity.getWard().getId() : null;
        this.classId = entity.getStudentClass() != null ? entity.getStudentClass().getId() : null;
        this.majorId = entity.getMajor() != null ? entity.getMajor().getId() : null;
        this.residenceStatus = entity.getResidenceStatus();
        this.academicYear = entity.getAcademicYear();
    }

    // Phương thức tạo DTO từ entity (tùy chọn)
    public static StudentDto fromEntity(Student entity) {
        return new StudentDto(entity);
    }
}
