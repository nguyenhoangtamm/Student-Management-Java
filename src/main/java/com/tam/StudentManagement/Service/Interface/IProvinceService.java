package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Province.ProvinceDto;
import com.tam.StudentManagement.Dto.Province.CreateProvinceDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Province;
import com.tam.StudentManagement.Request.Province.CreateProvinceRequest;
import com.tam.StudentManagement.Request.Province.UpdateProvinceRequest;

import java.util.List;
import java.util.Optional;

public interface IProvinceService {
    List<ProvinceDto> getAllProvinces();

    Optional<Province> getProvinceById(Integer id);

    CreateProvinceDto createProvince(CreateProvinceRequest request);

    Province updateProvince(Integer id, UpdateProvinceRequest request);

    String deleteProvince(Integer id);

    PaginationDto<ProvinceDto> getProvincesByPagination(int pageNumber, int pageSize, String keyword);
}