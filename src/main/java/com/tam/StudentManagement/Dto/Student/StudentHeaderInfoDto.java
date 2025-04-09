package com.tam.StudentManagement.Dto.Student;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentHeaderInfoDto {

    private String fullName;
    private String avatar;
    private List<StudentNotificationDto> notifications;
}