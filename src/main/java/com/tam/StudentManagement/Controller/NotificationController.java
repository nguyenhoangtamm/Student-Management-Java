package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Notification.CreateNotificationDto;
import com.tam.StudentManagement.Model.Notification;
import com.tam.StudentManagement.Request.Notification.CreateNotificationRequest;
import com.tam.StudentManagement.Request.Notification.UpdateNotificationRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.INotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Notification>>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(ApiResponse.success("Get notifications successfully", notifications));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Notification>> getNotificationById(@PathVariable Integer id) {
        return notificationService.getNotificationById(id)
                .map(notification -> ResponseEntity
                        .ok(ApiResponse.success("Get notification successfully", notification)))
                .orElse(ResponseEntity.ok(ApiResponse.error("Notification not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateNotificationDto>> createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {
        CreateNotificationDto createdNotification = notificationService.createNotification(request);
        return ResponseEntity.ok(ApiResponse.success("Create notification successfully", createdNotification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Notification>> updateNotification(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateNotificationRequest request) {
        Notification updatedNotification = notificationService.updateNotification(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update notification successfully", updatedNotification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteNotification(@PathVariable Integer id) {
        String message = notificationService.deleteNotification(id);
        return ResponseEntity.ok(ApiResponse.success("Delete notification successfully", message));
    }

    // @GetMapping("/pagination")
    // public ResponseEntity<ApiResponse<PaginationDto<NotificationDto>>> getNotificationsByPagination(
    //         @RequestParam(defaultValue = "1") int pageNumber,
    //         @RequestParam(defaultValue = "10") int pageSize,
    //         @RequestParam(required = false) String keyword) {
    //     PaginationDto<NotificationDto> data = notificationService.getNotificationsByPagination(pageNumber, pageSize,
    //             keyword);
    //     return ResponseEntity.ok(ApiResponse.success("Get notifications by pagination successfully", data));
    // }

    // @GetMapping("/student/{studentId}/unread")
    // public ResponseEntity<ApiResponse<List<Notification>>> getUnreadNotificationsByStudentId(
    //         @PathVariable Integer studentId) {
    //     List<Notification> notifications = notificationService.getUnreadNotificationsByStudentId(studentId);
    //     return ResponseEntity.ok(ApiResponse.success("Get unread notifications successfully", notifications));
    // }

    // @PutMapping("/{id}/read")
    // public ResponseEntity<ApiResponse<String>> markNotificationAsRead(@PathVariable Integer id) {
    //     String message = notificationService.markNotificationAsRead(id);
    //     return ResponseEntity.ok(ApiResponse.success("Mark notification as read successfully", message));
    // }
}