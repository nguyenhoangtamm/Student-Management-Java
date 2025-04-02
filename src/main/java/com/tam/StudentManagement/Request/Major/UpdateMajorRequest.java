package com.tam.StudentManagement.Request.Major;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMajorRequest {
    @NotBlank(message = "Mã ngành không được để trống")
    @Size(max = 30, message = "Mã ngành tối đa 30 ký tự")
    private String code;

    @NotBlank(message = "Tên ngành không được để trống")
    @Size(max = 255, message = "Tên ngành tối đa 255 ký tự")
    private String name;
}