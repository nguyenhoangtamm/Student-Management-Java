package com.tam.StudentManagement.Dto.Student;

import com.tam.StudentManagement.Dto.Notification.NotificationDto;

import lombok.Data;

@Data
public class StudentHeaderInfoDto {

    private String fullName;
    private String avatar;
    private NotificationDto notification;
}