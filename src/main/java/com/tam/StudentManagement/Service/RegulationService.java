package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Regulation.RegulationDto;
import com.tam.StudentManagement.Dto.Regulation.CreateRegulationDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.NotFoundException;
import com.tam.StudentManagement.Model.Regulation;
import com.tam.StudentManagement.Repository.RegulationRepository;
import com.tam.StudentManagement.Request.Regulation.CreateRegulationRequest;
import com.tam.StudentManagement.Request.Regulation.UpdateRegulationRequest;
import com.tam.StudentManagement.Service.Interface.IRegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegulationService implements IRegulationService {
    @Autowired
    private RegulationRepository regulationRepository;

    @Override
    public List<Regulation> getAllRegulations() {
        return regulationRepository.findAll();
    }

    @Override
    public Optional<Regulation> getRegulationById(Integer id) {
        return regulationRepository.findById(id);
    }

    @Override
    public CreateRegulationDto createRegulation(CreateRegulationRequest request) {

        Regulation entity = new Regulation();
        entity.setName(request.getName());
        entity.setUrl(request.getUrl());
        entity.setType(request.getType());
        entity.setIsActive(request.getIsActive());
        entity.setEffectiveDate(request.getEffectiveDate());

        regulationRepository.save(entity);

        return new CreateRegulationDto(entity);
    }

    @Override
    public Regulation updateRegulation(Integer id, UpdateRegulationRequest request) {
        return regulationRepository.findById(id).map(regulation -> {

            if (request.getName() != null) {
                regulation.setName(request.getName());
            }

            if (request.getIsActive() != null) {
                regulation.setIsActive(request.getIsActive());
            }

            return regulationRepository.save(regulation);
        }).orElseThrow(() -> new NotFoundException("Regulation not found"));
    }

    @Override
    public String deleteRegulation(Integer id) {
        Regulation entity = regulationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Regulation not found with id: " + id));
        regulationRepository.deleteById(id);
        return "Regulation deleted successfully";
    }

    @Override
    public PaginationDto<RegulationDto> getRegulationsByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Regulation> regulationPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            regulationPage = regulationRepository.findByNameContaining(keyword, pageable);
        } else {
            regulationPage = regulationRepository.findAll(pageable);
        }

        List<RegulationDto> regulationDtos = regulationPage.getContent().stream()
                .map(RegulationDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                regulationPage.getTotalElements(),
                regulationPage.getTotalPages());

        return new PaginationDto<>(regulationDtos, paginationInfo);
    }
}