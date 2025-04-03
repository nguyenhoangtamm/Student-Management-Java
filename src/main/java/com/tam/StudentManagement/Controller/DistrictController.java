package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.District.CreateDistrictDto;
import com.tam.StudentManagement.Dto.District.DistrictDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.District;
import com.tam.StudentManagement.Request.District.CreateDistrictRequest;
import com.tam.StudentManagement.Request.District.UpdateDistrictRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IDistrictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<District>>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return ResponseEntity.ok(ApiResponse.success("Get districts successfully", districts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<District>> getDistrictById(@PathVariable Integer id) {
        return districtService.getDistrictById(id)
                .map(district -> ResponseEntity.ok(ApiResponse.success("Get district successfully", district)))
                .orElse(ResponseEntity.ok(ApiResponse.error("District not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateDistrictDto>> createDistrict(
            @Valid @RequestBody CreateDistrictRequest request) {
        CreateDistrictDto createdDistrict = districtService.createDistrict(request);
        return ResponseEntity.ok(ApiResponse.success("Create district successfully", createdDistrict));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<District>> updateDistrict(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateDistrictRequest request) {
        District updatedDistrict = districtService.updateDistrict(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update district successfully", updatedDistrict));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDistrict(@PathVariable Integer id) {
        String message = districtService.deleteDistrict(id);
        return ResponseEntity.ok(ApiResponse.success("Delete district successfully", message));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<DistrictDto>>> getDistrictsByPagination(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        PaginationDto<DistrictDto> data = districtService.getDistrictsByPagination(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(ApiResponse.success("Get districts by pagination successfully", data));
    }
}