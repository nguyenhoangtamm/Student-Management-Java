package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Major.MajorDto;
import com.tam.StudentManagement.Dto.Major.CreateMajorDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Major;
import com.tam.StudentManagement.Request.Major.CreateMajorRequest;
import com.tam.StudentManagement.Request.Major.UpdateMajorRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IMajorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
public class MajorController {

    @Autowired
    private IMajorService majorService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<MajorDto>>> getAllMajors() {
        List<MajorDto> majors = majorService.getAllMajors();
        return ResponseEntity.ok(ApiResponse.success("Get majors successfully", majors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Major>> getMajorById(@PathVariable Integer id) {
        return majorService.getMajorById(id)
                .map(major -> ResponseEntity.ok(ApiResponse.success("Get major successfully", major)))
                .orElseGet(() -> ResponseEntity.ok(ApiResponse.error("Major not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateMajorDto>> createMajor(
            @Valid @RequestBody CreateMajorRequest request) {
        CreateMajorDto createdMajor = majorService.createMajor(request);
        return ResponseEntity.ok(ApiResponse.success("Create major successfully", createdMajor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Major>> updateMajor(@PathVariable Integer id,
            @Valid @RequestBody UpdateMajorRequest request) {
        Major updatedMajor = majorService.updateMajor(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update major successfully", updatedMajor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMajor(@PathVariable Integer id) {
        String message = majorService.deleteMajor(id);
        return ResponseEntity.ok(ApiResponse.success("Delete major successfully", message));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<MajorDto>>> getMajorsByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(required = false) String keyword) {
        PaginationDto<MajorDto> data = majorService.getMajorsByPagination(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(ApiResponse.success("Get majors by pagination successfully", data));
    }   
}
