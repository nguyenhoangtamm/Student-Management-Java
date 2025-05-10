package com.tam.StudentManagement.Request.Notification;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateNotificationRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;
    private Integer type;
}