package com.tam.StudentManagement.Dto.Major;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.Major;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
    private Integer id;
    private String code;
    private String name;

    // Constructor chuyển đổi từ entity sang DTO
    public MajorDto(Major entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
    }

    // Phương thức tạo DTO từ entity (tùy chọn)
    public static MajorDto fromEntity(Major entity) {
        return new MajorDto(entity);
    }
}