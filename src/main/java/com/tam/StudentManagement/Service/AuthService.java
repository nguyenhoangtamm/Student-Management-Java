package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Auth.LoginDto;

import jakarta.servlet.http.HttpServletResponse;

import com.tam.StudentManagement.Dto.Auth.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse login(LoginDto loginDto, HttpServletResponse response);
    void logout(HttpServletResponse response); // Thêm phương thức logout
}
