package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Notification.NotificationDto;
import com.tam.StudentManagement.Dto.Notification.CreateNotificationDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.NotFoundException;
import com.tam.StudentManagement.Model.Notification;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Repository.NotificationRepository;
import com.tam.StudentManagement.Repository.StudentRepository;
import com.tam.StudentManagement.Request.Notification.CreateNotificationRequest;
import com.tam.StudentManagement.Request.Notification.UpdateNotificationRequest;
import com.tam.StudentManagement.Service.Interface.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    @Override
    public CreateNotificationDto createNotification(CreateNotificationRequest request) {
        // Check if student exists
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        Notification entity = new Notification();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setType(request.getType());
        entity.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(entity);

        return new CreateNotificationDto(entity);
    }

    @Override
    public Notification updateNotification(Integer id, UpdateNotificationRequest request) {
        return notificationRepository.findById(id).map(notification -> {
            if (request.getTitle() != null) {
                notification.setTitle(request.getTitle());
            }

            if (request.getContent() != null) {
                notification.setContent(request.getContent());
            }

            if (request.getType() != null) {
                notification.setType(request.getType());
            }


            return notificationRepository.save(notification);
        }).orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    @Override
    public String deleteNotification(Integer id) {
        Notification entity = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found with id: " + id));
        notificationRepository.deleteById(id);
        return "Notification deleted successfully";
    }

    // @Override
    // public PaginationDto<NotificationDto> getNotificationsByPagination(int pageNumber, int pageSize, String keyword) {
    //     Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
    //     Page<Notification> notificationPage;

    //     if (keyword != null && !keyword.trim().isEmpty()) {
    //         notificationPage = notificationRepository.findByTitleContainingOrContentContaining(keyword, keyword,
    //                 pageable);
    //     } else {
    //         notificationPage = notificationRepository.findAll(pageable);
    //     }

    //     List<NotificationDto> notificationDtos = notificationPage.getContent().stream()
    //             .map(NotificationDto::new)
    //             .collect(Collectors.toList());

    //     PaginationInfo paginationInfo = new PaginationInfo(
    //             pageNumber,
    //             pageSize,
    //             notificationPage.getTotalElements(),
    //             notificationPage.getTotalPages());

    //     return new PaginationDto<>(notificationDtos, paginationInfo);
    // }

    // @Override
    // public List<Notification> getUnreadNotificationsByStudentId(Integer studentId) {
    //     return notificationRepository.findByStudentIdAndIsReadFalse(studentId);
    // }

    // @Override
    // public String markNotificationAsRead(Integer id) {
    //     return notificationRepository.findById(id).map(notification -> {
    //         notificationRepository.save(notification);
    //         return "Notification marked as read successfully";
    //     }).orElseThrow(() -> new NotFoundException("Notification not found"));
    // }
}