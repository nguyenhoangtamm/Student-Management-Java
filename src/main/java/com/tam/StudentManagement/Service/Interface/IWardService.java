package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Ward.WardDto;
import com.tam.StudentManagement.Dto.Ward.CreateWardDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Ward;
import com.tam.StudentManagement.Request.Ward.CreateWardRequest;
import com.tam.StudentManagement.Request.Ward.UpdateWardRequest;

import java.util.List;
import java.util.Optional;

public interface IWardService {
    List<Ward> getAllWards();

    Optional<Ward> getWardById(Integer id);

    CreateWardDto createWard(CreateWardRequest request);

    Ward updateWard(Integer id, UpdateWardRequest request);

    String deleteWard(Integer id);

    PaginationDto<WardDto> getWardsByPagination(int pageNumber, int pageSize, String keyword);
}