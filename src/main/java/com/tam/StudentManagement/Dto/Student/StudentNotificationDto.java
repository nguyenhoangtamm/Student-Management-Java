package com.tam.StudentManagement.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentNotificationDto {
    private Integer id;
    private String title;
    private String slug;
    private String content;
    private String date;
    private Integer type;
    private Boolean isRead;
    
}