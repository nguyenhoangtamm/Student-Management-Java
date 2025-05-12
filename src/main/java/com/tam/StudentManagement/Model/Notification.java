package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private Integer type;

    @Column(name = "views")
    private Integer views;

    @Column(name = "slug")
    private String slug;

    @Column(name = "is_send")
    private boolean isSend;

    @OneToMany(mappedBy = "notification")
    @JsonIgnore
    private List<StudentNotification> studentNotifications;
}
