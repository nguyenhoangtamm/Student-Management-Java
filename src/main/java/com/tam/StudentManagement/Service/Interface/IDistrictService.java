package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.District.DistrictDto;
import com.tam.StudentManagement.Dto.District.CreateDistrictDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.District;
import com.tam.StudentManagement.Request.District.CreateDistrictRequest;
import com.tam.StudentManagement.Request.District.UpdateDistrictRequest;

import java.util.List;
import java.util.Optional;

public interface IDistrictService {
    List<District> getAllDistricts();

    Optional<District> getDistrictById(Integer id);

    CreateDistrictDto createDistrict(CreateDistrictRequest request);

    District updateDistrict(Integer id, UpdateDistrictRequest request);

    String deleteDistrict(Integer id);

    PaginationDto<DistrictDto> getDistrictsByPagination(int pageNumber, int pageSize, String keyword);
}