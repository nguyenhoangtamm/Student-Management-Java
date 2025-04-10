package com.tam.StudentManagement.Dto.Auth;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponse {
    private boolean success;
    private String token;
    private UserLogin user;
}