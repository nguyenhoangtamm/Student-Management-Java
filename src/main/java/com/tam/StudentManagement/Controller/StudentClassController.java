package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.StudentClass.CreateStudentClassDto;
import com.tam.StudentManagement.Dto.StudentClass.StudentClassDto;
import com.tam.StudentManagement.Model.StudentClass;
import com.tam.StudentManagement.Request.StudentClass.CreateStudentClassRequest;
import com.tam.StudentManagement.Request.StudentClass.UpdateStudentClassRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IStudentClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-classes")
public class StudentClassController {
    @Autowired
    private IStudentClassService studentClassService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentClass>>> getAllStudentClasses() {
        List<StudentClass> studentClasses = studentClassService.getAllStudentClasses();
        return ResponseEntity.ok(ApiResponse.success("Get student classes successfully", studentClasses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentClass>> getStudentClassById(@PathVariable Integer id) {
        return studentClassService.getStudentClassById(id)
                .map(studentClass -> ResponseEntity
                        .ok(ApiResponse.success("Get student class successfully", studentClass)))
                .orElse(ResponseEntity.ok(ApiResponse.error("Student class not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateStudentClassDto>> createStudentClass(
            @Valid @RequestBody CreateStudentClassRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Create student class successfully",
                studentClassService.createStudentClass(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentClass>> updateStudentClass(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateStudentClassRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Update student class successfully",
                studentClassService.updateStudentClass(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudentClass(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Delete student class successfully",
                studentClassService.deleteStudentClass(id)));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<StudentClassDto>>> getStudentClassesByPagination(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(ApiResponse.success("Get student classes by pagination successfully",
                studentClassService.getStudentClassesByPagination(pageNumber, pageSize, keyword)));
    }
}