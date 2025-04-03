package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Dormitory.DormitoryDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Dormitory.CreateDormitoryDto;
import com.tam.StudentManagement.Model.Dormitory;
import com.tam.StudentManagement.Request.Dormitory.CreateDormitoryRequest;
import com.tam.StudentManagement.Request.Dormitory.UpdateDormitoryRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IDormitoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dormitories")
public class DormitoryController {

    @Autowired
    private IDormitoryService dormitoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dormitory>>> getAllDormitories() {
        List<Dormitory> dormitories = dormitoryService.getAllDormitories();
        return ResponseEntity.ok(ApiResponse.success("Get dormitories successfully", dormitories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Dormitory>> getDormitoryById(@PathVariable Integer id) {
        return dormitoryService.getDormitoryById(id)
                .map(dormitory -> ResponseEntity.ok(ApiResponse.success("Get dormitory successfully", dormitory)))
                .orElseGet(() -> ResponseEntity.ok(ApiResponse.error("Dormitory not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateDormitoryDto>> createDormitory(
            @Valid @RequestBody CreateDormitoryRequest request) {
        CreateDormitoryDto createdDormitory = dormitoryService.createDormitory(request);
        return ResponseEntity.ok(ApiResponse.success("Create dormitory successfully", createdDormitory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Dormitory>> updateDormitory(@PathVariable Integer id,
            @Valid @RequestBody UpdateDormitoryRequest request) {
        Dormitory updatedDormitory = dormitoryService.updateDormitory(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update dormitory successfully", updatedDormitory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDormitory(@PathVariable Integer id) {
        String message = dormitoryService.deleteDormitory(id);
        return ResponseEntity.ok(ApiResponse.success("Delete dormitory successfully", message));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<DormitoryDto>>> getDormitoriesByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(required = false) String keyword) {
        PaginationDto<DormitoryDto> data = dormitoryService.getDormitoriesByPagination(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(ApiResponse.success("Get dormitories by pagination successfully", data));
    }
}