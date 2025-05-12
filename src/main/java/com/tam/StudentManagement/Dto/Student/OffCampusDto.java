package com.tam.StudentManagement.Dto.Student;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffCampusDto {

    private String name;
    private String address;
    private String ownerName;
    private String phoneNumber;
    private String contractStatus;
    private String room;
    private String price;
    private List<StudentServiceDto> services;
}
