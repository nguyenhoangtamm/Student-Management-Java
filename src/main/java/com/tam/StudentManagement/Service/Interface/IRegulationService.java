package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Regulation.RegulationDto;
import com.tam.StudentManagement.Dto.Regulation.CreateRegulationDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Regulation;
import com.tam.StudentManagement.Request.Regulation.CreateRegulationRequest;
import com.tam.StudentManagement.Request.Regulation.UpdateRegulationRequest;

import java.util.List;
import java.util.Optional;

public interface IRegulationService {
    List<Regulation> getAllRegulations();

    Optional<Regulation> getRegulationById(Integer id);

    CreateRegulationDto createRegulation(CreateRegulationRequest request);

    Regulation updateRegulation(Integer id, UpdateRegulationRequest request);

    String deleteRegulation(Integer id);

    PaginationDto<RegulationDto> getRegulationsByPagination(int pageNumber, int pageSize, String keyword);
}