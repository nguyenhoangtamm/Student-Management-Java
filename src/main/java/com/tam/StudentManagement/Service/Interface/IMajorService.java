package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Major.MajorDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Major.CreateMajorDto;
import com.tam.StudentManagement.Model.Major;
import com.tam.StudentManagement.Request.Major.CreateMajorRequest;
import com.tam.StudentManagement.Request.Major.UpdateMajorRequest;

import java.util.List;
import java.util.Optional;

public interface IMajorService {
    List<MajorDto> getAllMajors();

    Optional<Major> getMajorById(Integer id);

    CreateMajorDto createMajor(CreateMajorRequest request);

    Major updateMajor(Integer id, UpdateMajorRequest request);

    String deleteMajor(Integer id);

    PaginationDto<MajorDto> getMajorsByPagination(int pageNumber, int pageSize, String keyword);
}