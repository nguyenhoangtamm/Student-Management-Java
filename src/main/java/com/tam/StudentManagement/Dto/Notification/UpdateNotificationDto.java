package com.tam.StudentManagement.Dto.Notification;

import lombok.Data;

@Data
public class UpdateNotificationDto {
    private String title;
    private String content;
    private String type;
    private Boolean isRead;
    private Integer studentId;
}