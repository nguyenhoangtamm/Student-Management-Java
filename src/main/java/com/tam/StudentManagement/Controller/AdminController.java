package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Student.StudentStatusDto;
import com.tam.StudentManagement.Dto.Student.StudentStatisticsDto;

import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/get-status")
    public ResponseEntity<ApiResponse<StudentStatusDto>> getStatus() {
        StudentStatusDto status = studentService.getStatus();
        return ResponseEntity.ok(ApiResponse.success("Get status successfully",
                status));
    }

    @GetMapping("/get-statistics")
    public ResponseEntity<ApiResponse<StudentStatisticsDto>> getStatistics() {
        StudentStatisticsDto statistics = studentService.getStatistic();
        return ResponseEntity.ok(ApiResponse.success("Get statistics successfully",
                statistics));
    }

}
