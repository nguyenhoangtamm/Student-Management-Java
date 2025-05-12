package com.tam.StudentManagement.Dto.Student;

import com.tam.StudentManagement.Model.Student;
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
    private String phoneNumber;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private Integer classId;
    private Integer majorId;
    private Integer residenceStatus;
    private String academicYear;
    private String fullAddress; // Địa chỉ đầy đủ (tùy chọn)

    // Constructor chuyển đổi từ entity sang DTO
    public StudentDto(Student entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.fullName = entity.getFullName();
        this.gender = entity.getGender();
        this.dateOfBirth = entity.getDateOfBirth();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.provinceId = entity.getProvince() != null ? entity.getProvince().getId() : null;
        this.districtId = entity.getDistrict() != null ? entity.getDistrict().getId() : null;
        this.wardId = entity.getWard() != null ? entity.getWard().getId() : null;
        this.classId = entity.getStudentClass() != null ? entity.getStudentClass().getId() : null;
        this.majorId = entity.getMajor() != null ? entity.getMajor().getId() : null;
        this.residenceStatus = entity.getResidenceStatus();
        this.academicYear = entity.getAcademicYear();
        this.fullAddress = entity.getFullAddress(); // Địa chỉ đầy đủ (tùy chọn)
    }

    // Phương thức tạo DTO từ entity (tùy chọn)
    public static StudentDto fromEntity(Student entity) {
        return new StudentDto(entity);
    }
}
