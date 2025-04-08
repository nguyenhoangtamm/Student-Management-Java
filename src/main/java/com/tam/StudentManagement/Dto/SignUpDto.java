package com.tam.StudentManagement.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Provide a valid email address")
    private String email;

    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}