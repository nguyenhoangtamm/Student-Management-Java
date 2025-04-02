package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private int type;
    private int views;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDelete;

    @OneToMany(mappedBy = "notification")
    private List<StudentNotification> studentNotifications;
}
