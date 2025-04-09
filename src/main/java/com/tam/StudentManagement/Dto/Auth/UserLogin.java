package com.tam.StudentManagement.Dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogin {
    private String code;
    private Boolean isAdmin;

}
