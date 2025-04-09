package com.tam.StudentManagement.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentNotificationDto {
    private Integer id;
    private String title;
    // private String content;
    // private Integer type;
    // private LocalDateTime createdAt;
    // private Boolean isRead;
    private String slug;
    
}