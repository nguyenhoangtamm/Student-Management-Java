package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Auth.LoginDto;
import com.tam.StudentManagement.Dto.Auth.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse login(LoginDto loginDto);
}