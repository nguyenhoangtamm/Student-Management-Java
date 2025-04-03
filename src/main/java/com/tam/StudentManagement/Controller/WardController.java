package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Ward.CreateWardDto;
import com.tam.StudentManagement.Dto.Ward.WardDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Ward;
import com.tam.StudentManagement.Request.Ward.CreateWardRequest;
import com.tam.StudentManagement.Request.Ward.UpdateWardRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IWardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wards")
public class WardController {
    @Autowired
    private IWardService wardService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ward>>> getAllWards() {
        List<Ward> wards = wardService.getAllWards();
        return ResponseEntity.ok(ApiResponse.success("Get wards successfully", wards));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Ward>> getWardById(@PathVariable Integer id) {
        return wardService.getWardById(id)
                .map(ward -> ResponseEntity.ok(ApiResponse.success("Get ward successfully", ward)))
                .orElse(ResponseEntity.ok(ApiResponse.error("Ward not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateWardDto>> createWard(
            @Valid @RequestBody CreateWardRequest request) {
        CreateWardDto createdWard = wardService.createWard(request);
        return ResponseEntity.ok(ApiResponse.success("Create ward successfully", createdWard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Ward>> updateWard(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateWardRequest request) {
        Ward updatedWard = wardService.updateWard(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update ward successfully", updatedWard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteWard(@PathVariable Integer id) {
        String message = wardService.deleteWard(id);
        return ResponseEntity.ok(ApiResponse.success("Delete ward successfully", message));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<WardDto>>> getWardsByPagination(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        PaginationDto<WardDto> data = wardService.getWardsByPagination(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(ApiResponse.success("Get wards by pagination successfully", data));
    }
}