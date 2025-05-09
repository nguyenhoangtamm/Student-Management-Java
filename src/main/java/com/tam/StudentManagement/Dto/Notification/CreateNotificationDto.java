package com.tam.StudentManagement.Dto.Notification;

import lombok.Data;
import com.tam.StudentManagement.Model.Notification;

@Data
public class CreateNotificationDto {
    private Integer id;
    private String title;
    private String content;
    private Integer type;

    public CreateNotificationDto(Notification entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.type = entity.getType();
    }
}