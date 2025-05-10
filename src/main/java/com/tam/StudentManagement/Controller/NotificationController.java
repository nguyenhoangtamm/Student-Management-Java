package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Notification.CreateNotificationDto;
import com.tam.StudentManagement.Dto.Notification.NotificationDto;
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

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<Notification>>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(ApiResponse.success("Get notifications successfully", notifications));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<Notification>> getNotificationById(@PathVariable Integer id) {
        return notificationService.getNotificationById(id)
                .map(notification -> ResponseEntity
                        .ok(ApiResponse.success("Get notification successfully", notification)))
                .orElse(ResponseEntity.ok(ApiResponse.error("Notification not found")));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateNotificationDto>> createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {
        CreateNotificationDto createdNotification = notificationService.createNotification(request);
        return ResponseEntity.ok(ApiResponse.success("Create notification successfully", createdNotification));
    }
    // @PostMapping("/create")
    // public ResponseEntity<ApiResponse<String>> createNotification(
    // @Valid @RequestBody CreateNotificationRequest request) {
    // // CreateNotificationDto createdNotification =
    // notificationService.createNotification(request);
    // return ResponseEntity.ok(ApiResponse.success("Create notification
    // successfully"));
    // }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<Notification>> updateNotification(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateNotificationRequest request) {
        Notification updatedNotification = notificationService.updateNotification(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update notification successfully", updatedNotification));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteNotification(@PathVariable Integer id) {
        String message = notificationService.deleteNotification(id);
        return ResponseEntity.ok(ApiResponse.success("Delete notification successfully", message));
    }

    @GetMapping("/get-paging")
    public ResponseEntity<ApiResponse<PaginationDto<NotificationDto>>>
    getNotificationsByPagination(
    @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(required = false) String keyword) {
    PaginationDto<NotificationDto> data = notificationService.getNotificationsByPagination(pageNumber, pageSize,keyword);
    return ResponseEntity.ok(ApiResponse.success("Get notifications by paginationsuccessfully", data));
    }

    @PostMapping("/read/{id}")
    public ResponseEntity<ApiResponse<String>> readNotification(@PathVariable Integer id) {
        String message = notificationService.readNotification(id);
        return ResponseEntity.ok(ApiResponse.success("Read notification successfully", message));
    }
}