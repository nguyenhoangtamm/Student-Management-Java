package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Student.StudentDto;
import com.tam.StudentManagement.Dto.Student.StudentDashboardDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Student.CreateStudentDto;
import com.tam.StudentManagement.Dto.Student.StudentHeaderInfoDto;
import com.tam.StudentManagement.Dto.Student.StudentProfileDto;
import com.tam.StudentManagement.Dto.Student.StudentContractDto;
import com.tam.StudentManagement.Dto.Student.StudentStatusDto;
import com.tam.StudentManagement.Dto.Student.StudentStatisticsDto;
import com.tam.StudentManagement.Dto.Student.StudentNotificationDto;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Request.Student.CreateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateOffCampusRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    // Lấy danh sách sinh viên
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.success("Get students successfully", students));
    }

    // Lấy thông tin sinh viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok(ApiResponse.success("Get student successfully", student)))
                .orElseGet(() -> ResponseEntity.ok(ApiResponse.error("Student not found")));
    }

    // Thêm mới sinh viên
    @PostMapping
    public ResponseEntity<ApiResponse<CreateStudentDto>> createStudent(
            @Valid @RequestBody CreateStudentRequest student) {
        CreateStudentDto createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(ApiResponse.success("Create student successfully", createdStudent));
    }

    // Cập nhật thông tin sinh viên
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Integer id,
            @Valid @RequestBody UpdateStudentRequest studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(ApiResponse.success("Update student successfully", updatedStudent));
    }

    // Xóa sinh viên (đánh dấu isDelete thay vì xóa thật sự)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Integer id) {
        String message = studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.success("Delete student successfully", message));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<StudentDashboardDto>> getDashboard() {
        StudentDashboardDto data = studentService.getDashboard();
        return ResponseEntity.ok(ApiResponse.success("Get dashboard successfully", data));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<StudentDto>>> getStudentsByPagination(@RequestParam int pageNumber,
            @RequestParam int pageSize, @RequestParam String keyword) {
        PaginationDto<StudentDto> data = studentService.getStudentsByPagination(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(ApiResponse.success("Get students by pagination successfully", data));
    }

    // @GetMapping("/header-info")
    // public ResponseEntity<ApiResponse<StudentHeaderInfoDto>> getHeaderInfo() {
    //     StudentHeaderInfoDto headerInfo = studentService.getHeaderInfo();
    //     return ResponseEntity.ok(ApiResponse.success("Get header info successfully", headerInfo));
    // }

    // @GetMapping("/profile")
    // public ResponseEntity<ApiResponse<StudentProfileDto>> getProfile() {
    //     StudentProfileDto profile = studentService.getProfile();
    //     return ResponseEntity.ok(ApiResponse.success("Get profile successfully", profile));
    // }

    // @GetMapping("/contract")
    // public ResponseEntity<ApiResponse<StudentContractDto>> getContract() {
    //     StudentContractDto contract = studentService.getContract();
    //     return ResponseEntity.ok(ApiResponse.success("Get contract successfully", contract));
    // }

    // @PutMapping("/off-campus")
    // public ResponseEntity<ApiResponse<StudentStatusDto>> updateOffCampus(
    //         @Valid @RequestBody UpdateOffCampusRequest request) {
    //     StudentStatusDto result = studentService.updateOffCampus(request);
    //     return ResponseEntity.ok(ApiResponse.success("Update off campus successfully", result));
    // }

    // @GetMapping("/status")
    // public ResponseEntity<ApiResponse<StudentStatusDto>> getStatus() {
    //     StudentStatusDto status = studentService.getStatus();
    //     return ResponseEntity.ok(ApiResponse.success("Get status successfully", status));
    // }

    // @GetMapping("/statistics")
    // public ResponseEntity<ApiResponse<StudentStatisticsDto>> getStatistics() {
    //     StudentStatisticsDto statistics = studentService.getStatistics();
    //     return ResponseEntity.ok(ApiResponse.success("Get statistics successfully", statistics));
    // }

    // @GetMapping("/notifications")
    // public ResponseEntity<ApiResponse<List<StudentNotificationDto>>> getStudentNotifications() {
    //     List<StudentNotificationDto> notifications = studentService.getStudentNotifications();
    //     return ResponseEntity.ok(ApiResponse.success("Get student notifications successfully", notifications));
    // }
}
