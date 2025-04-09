package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private Integer type;
    private Integer views;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDelete;

    @OneToMany(mappedBy = "notification")
    @JsonIgnore
    private List<StudentNotification> studentNotifications;
}
