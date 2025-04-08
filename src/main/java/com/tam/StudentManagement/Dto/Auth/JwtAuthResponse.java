package com.tam.StudentManagement.Dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Integer studentId;
    private String studentCode;
    private String fullName;
    private Boolean isAdmin;
}