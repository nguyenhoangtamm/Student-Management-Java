package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "student_notifications")
public class StudentNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
