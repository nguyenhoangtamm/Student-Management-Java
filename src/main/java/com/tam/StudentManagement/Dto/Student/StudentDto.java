package com.tam.StudentManagement.Dto.Student;

import com.tam.StudentManagement.Model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Integer id;
    private String code;
    private String fullName;
    private String email;
    private String phoneNumber;

    // Constructor chuyển đổi từ entity sang DTO
    public StudentDto(Student entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.fullName = entity.getFullName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
    }

    // Phương thức tạo DTO từ entity (tùy chọn)
    public static StudentDto fromEntity(Student entity) {
        return new StudentDto(entity);
    }
}
