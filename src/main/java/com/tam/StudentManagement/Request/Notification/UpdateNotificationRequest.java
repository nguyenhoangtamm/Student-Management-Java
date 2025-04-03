package com.tam.StudentManagement.Request.Notification;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNotificationRequest {
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    private String content;

    private Integer type;

    private Boolean isRead;
    private Integer studentId;
}