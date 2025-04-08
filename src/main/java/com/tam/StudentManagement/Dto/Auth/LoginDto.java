package com.tam.StudentManagement.Dto.Auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty(message = "Mã sinh viên không được để trống")
    private String code;

    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;
}