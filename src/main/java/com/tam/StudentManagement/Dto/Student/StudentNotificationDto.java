package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentNotificationDto {
    private Long id;
    private String title;
    private String content;
    private String type;
    private LocalDateTime createdAt;
    private Boolean isRead;
    private String link;
}