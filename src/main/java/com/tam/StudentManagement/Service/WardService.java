package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Ward.WardDto;
import com.tam.StudentManagement.Dto.Ward.CreateWardDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Exception.NotFoundException;
import com.tam.StudentManagement.Model.Ward;
import com.tam.StudentManagement.Model.District;
import com.tam.StudentManagement.Repository.WardRepository;
import com.tam.StudentManagement.Repository.DistrictRepository;
import com.tam.StudentManagement.Request.Ward.CreateWardRequest;
import com.tam.StudentManagement.Request.Ward.UpdateWardRequest;
import com.tam.StudentManagement.Service.Interface.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WardService implements IWardService {
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    @Override
    public Optional<Ward> getWardById(Integer id) {
        return wardRepository.findById(id);
    }

    @Override
    public CreateWardDto createWard(CreateWardRequest request) {
        // Check for duplicate code
        Ward existingWard = wardRepository.findByCode(request.getCode());
        if (existingWard != null) {
            throw new DuplicateException("Ward code already exists");
        }

        // Check if district exists
        District district = districtRepository.findById(request.getDistrictId())
                .orElseThrow(() -> new NotFoundException("District not found"));

        Ward entity = new Ward();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setDistrict(district);

        wardRepository.save(entity);

        return new CreateWardDto(entity);
    }

    @Override
    public Ward updateWard(Integer id, UpdateWardRequest request) {
        return wardRepository.findById(id).map(ward -> {
            // Check for duplicate code if code is being updated
            if (request.getCode() != null && !request.getCode().equals(ward.getCode())) {
                Ward existingWard = wardRepository.findByCode(request.getCode());
                if (existingWard != null) {
                    throw new DuplicateException("Ward code already exists");
                }
                ward.setCode(request.getCode());
            }

            if (request.getName() != null) {
                ward.setName(request.getName());
            }

            if (request.getType() != null) {
                ward.setType(request.getType());
            }

            if (request.getDistrictId() != null) {
                District district = districtRepository.findById(request.getDistrictId())
                        .orElseThrow(() -> new NotFoundException("District not found"));
                ward.setDistrict(district);
            }

            return wardRepository.save(ward);
        }).orElseThrow(() -> new NotFoundException("Ward not found"));
    }

    @Override
    public String deleteWard(Integer id) {
        Ward entity = wardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ward not found with id: " + id));
        wardRepository.deleteById(id);
        return "Ward deleted successfully";
    }

    @Override
    public PaginationDto<WardDto> getWardsByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Ward> wardPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            wardPage = wardRepository.findByCodeContainingOrNameContaining(keyword, keyword, pageable);
        } else {
            wardPage = wardRepository.findAll(pageable);
        }

        List<WardDto> wardDtos = wardPage.getContent().stream()
                .map(WardDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                wardPage.getTotalElements(),
                wardPage.getTotalPages());

        return new PaginationDto<>(wardDtos, paginationInfo);
    }
}