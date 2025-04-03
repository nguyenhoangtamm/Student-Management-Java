package com.tam.StudentManagement.Dto.Student;

import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPaginationResponse {
    private List<StudentDto> data;
    private PaginationInfo pagination;
}