package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDashboardDto {
    private String code;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String birthplace;
    private String faculty;
    private String email;
    private String avatar;
    private Integer unreadNotifications;
    private OffCampusInfo offCampusInfo;
    private List<StudentNotificationDto> notifications;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OffCampusInfo {
        private String name;
        private String status;
        private String updatedAt;
        private String room;
        private String address;
    }
}