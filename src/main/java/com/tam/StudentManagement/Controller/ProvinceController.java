package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Province.CreateProvinceDto;
import com.tam.StudentManagement.Dto.Province.ProvinceDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Province;
import com.tam.StudentManagement.Request.Province.CreateProvinceRequest;
import com.tam.StudentManagement.Request.Province.UpdateProvinceRequest;
import com.tam.StudentManagement.Response.ApiResponse;
import com.tam.StudentManagement.Service.Interface.IProvinceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Province>>> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        return ResponseEntity.ok(ApiResponse.success("Get provinces successfully", provinces));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Province>> getProvinceById(@PathVariable Integer id) {
        return provinceService.getProvinceById(id)
                .map(province -> ResponseEntity.ok(ApiResponse.success("Get province successfully", province)))
                .orElse(ResponseEntity.ok(ApiResponse.error("Province not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateProvinceDto>> createProvince(
            @Valid @RequestBody CreateProvinceRequest request) {
        CreateProvinceDto createdProvince = provinceService.createProvince(request);
        return ResponseEntity.ok(ApiResponse.success("Create province successfully", createdProvince));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Province>> updateProvince(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProvinceRequest request) {
        Province updatedProvince = provinceService.updateProvince(id, request);
        return ResponseEntity.ok(ApiResponse.success("Update province successfully", updatedProvince));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProvince(@PathVariable Integer id) {
        String message = provinceService.deleteProvince(id);
        return ResponseEntity.ok(ApiResponse.success("Delete province successfully", message));
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<PaginationDto<ProvinceDto>>> getProvincesByPagination(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        PaginationDto<ProvinceDto> data = provinceService.getProvincesByPagination(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(ApiResponse.success("Get provinces by pagination successfully", data));
    }
}