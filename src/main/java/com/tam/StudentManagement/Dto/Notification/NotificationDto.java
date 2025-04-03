package com.tam.StudentManagement.Dto.Notification;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.Notification;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Integer id;
    private String title;
    private String content;
    private Integer type;
    private Boolean isRead;
    private Integer studentId;
    private String createdAt;

    public NotificationDto(Notification entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.type = entity.getType();
        this.createdAt = entity.getCreatedAt().toString();
    }
}