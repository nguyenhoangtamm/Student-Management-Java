package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Notification.NotificationDto;
import com.tam.StudentManagement.Dto.Notification.CreateNotificationDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Notification;
import com.tam.StudentManagement.Request.Notification.CreateNotificationRequest;
import com.tam.StudentManagement.Request.Notification.UpdateNotificationRequest;

import java.util.List;
import java.util.Optional;

public interface INotificationService {
    List<Notification> getAllNotifications();

    Optional<Notification> getNotificationById(Integer id);

    CreateNotificationDto createNotification(CreateNotificationRequest request);

    Notification updateNotification(Integer id, UpdateNotificationRequest request);

    String deleteNotification(Integer id);

    PaginationDto<NotificationDto> getNotificationsByPagination(int pageNumber, int pageSize, String keyword);

    String readNotification (Integer id);
}