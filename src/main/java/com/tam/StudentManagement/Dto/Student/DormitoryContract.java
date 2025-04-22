package com.tam.StudentManagement.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryContract {
    private String dormitoryName;
    private String fullAddress;
    private String address;
    private String wardName;
    private String districtName;
    private String provinceName;
    private String ownerName;
    private String phoneNumber;
    private List<DormitoryServiceDto> services;
}

