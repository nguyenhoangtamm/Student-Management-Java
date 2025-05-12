package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Dto.Notification.CreateNotificationDto;
import com.tam.StudentManagement.Dto.Notification.NotificationDto;
import com.tam.StudentManagement.Exception.NotFoundException;
import com.tam.StudentManagement.Model.Notification;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Model.StudentNotification;
import com.tam.StudentManagement.Repository.NotificationRepository;
import com.tam.StudentManagement.Repository.StudentNotificationRepository;
import com.tam.StudentManagement.Repository.StudentRepository;
import com.tam.StudentManagement.Request.Notification.CreateNotificationRequest;
import com.tam.StudentManagement.Request.Notification.UpdateNotificationRequest;
import com.tam.StudentManagement.Security.StudentDetails;
import com.tam.StudentManagement.Service.Interface.INotificationService;
import com.tam.StudentManagement.Util.SlugUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private StudentNotificationRepository studentNotificationRepository;

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
        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (request.getContent() == null || request.getContent().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        if (request.getType() == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        String slug = SlugUtil.toSlug(request.getTitle());
        Notification entity = new Notification();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setType(request.getType());
        entity.setSlug(slug);
        entity.setViews(0);
        entity.setSend(false);
        entity.setIsDelete(false);
        entity.setCreatedAt(java.time.Instant.ofEpochMilli(System.currentTimeMillis())
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime());

        notificationRepository.save(entity);

        return new CreateNotificationDto(entity);
    }

    @Override
    public Notification updateNotification(Integer id, UpdateNotificationRequest request) {
        return notificationRepository.findById(id)
                .filter(notification -> !notification.getIsDelete())
                .map(notification -> {
                    if (request.getTitle() != null) {
                        notification.setTitle(request.getTitle());
                        String slug = SlugUtil.toSlug(request.getTitle());
                        notification.setSlug(slug);
                    }

                    if (request.getContent() != null) {
                        notification.setContent(request.getContent());
                    }

                    if (request.getType() != null) {
                        notification.setType(request.getType());
                    }

                    return notificationRepository.save(notification);
                }).orElseThrow(() -> new NotFoundException("Notification not found or has been deleted"));
    }

    @Override
    public String deleteNotification(Integer id) {
        Notification entity = notificationRepository.findById(id)
                .filter(notification -> !notification.getIsDelete())
                .orElseThrow(() -> new NotFoundException("Notification not found with id: " + id));
        entity.setIsDelete(true);
        notificationRepository.save(entity);
        return "Notification marked as deleted successfully";
    }

    @Override
    public PaginationDto<NotificationDto> getNotificationsByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Notification> notificationPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            notificationPage = notificationRepository.findByTitleContainingOrContentContaining(keyword, keyword,
                    pageable);
        } else {
            notificationPage = notificationRepository.findAll(pageable);
        }

        List<NotificationDto> notificationDtos = notificationPage.getContent().stream()
                .filter(notifica -> !notifica.getIsDelete())
                .sorted((d1, d2) -> d1.getTitle().compareTo(d2.getTitle()))

                .map(NotificationDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                notificationPage.getTotalElements(),
                notificationPage.getTotalPages());

        return new PaginationDto<>(notificationDtos, paginationInfo);
    }

    @Override
    public String readNotification(Integer id) {
        Student student = new Student();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof StudentDetails) {
            StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
            student = studentDetails.getStudent();
        }
        StudentNotification studentNotification = new StudentNotification();
        Notification notification = notificationRepository.findById(id)
                .filter(notifica -> !notifica.getIsDelete())
                .orElseThrow(() -> new NotFoundException("Notification not found with id: " + id));
        studentNotification.setNotification(notification);
        studentNotification.setRead(true);
        studentNotification.setStudent(student);
        studentNotificationRepository.save(studentNotification);
        return "Notification marked as read successfully";
    }

    @Override
    public String sendNotification(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .filter(notifica -> !notifica.getIsDelete())
                .orElseThrow(() -> new NotFoundException("Notification not found with id: " + id));
        notification.setSend(true);

        notificationRepository.save(notification);
        return "Notification sent successfully";
    }

}