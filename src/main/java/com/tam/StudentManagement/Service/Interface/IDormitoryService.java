package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Dormitory.DormitoryDto;
import com.tam.StudentManagement.Dto.Dormitory.DormitoryPaginationDto;
import com.tam.StudentManagement.Dto.Dormitory.DormitoryReviewDto;
import com.tam.StudentManagement.Dto.Dormitory.GetDormitoryBySlug;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Dormitory.CreateDormitoryDto;
import com.tam.StudentManagement.Model.Dormitory;
import com.tam.StudentManagement.Request.Dormitory.CreateDormitoryRequest;
import com.tam.StudentManagement.Request.Dormitory.UpdateDormitoryRequest;

import java.util.List;
import java.util.Optional;

public interface IDormitoryService {
    List<Dormitory> getAllDormitories();

    Optional<Dormitory> getDormitoryById(Integer id);

    CreateDormitoryDto createDormitory(CreateDormitoryRequest request);

    Dormitory updateDormitory(Integer id, UpdateDormitoryRequest request);

    String deleteDormitory(Integer id);

    PaginationDto<DormitoryPaginationDto> getDormitoriesByPagination(int pageNumber, int pageSize, String keyword);

    GetDormitoryBySlug getDormitoryBySlug(String slug);

    List<DormitoryReviewDto> getDormitoryReviewById(int id);
}