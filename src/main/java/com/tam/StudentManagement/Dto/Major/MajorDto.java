package com.tam.StudentManagement.Dto.Major;

import com.tam.StudentManagement.Model.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajorDto {
    private Integer id;
    private String code;
    private String name;
    private Integer totalStudent;

    // Constructor chuyển đổi từ entity sang DTO
    public MajorDto(Major entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.totalStudent = entity.getTotalStudent();
    }

    // Phương thức tạo DTO từ entity (tùy chọn)
    public static MajorDto fromEntity(Major entity) {
        return new MajorDto(entity);
    }
}