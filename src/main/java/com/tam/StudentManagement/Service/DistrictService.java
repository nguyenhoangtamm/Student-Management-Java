package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.District.DistrictDto;
import com.tam.StudentManagement.Dto.District.CreateDistrictDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Exception.NotFoundException;
import com.tam.StudentManagement.Model.District;
import com.tam.StudentManagement.Model.Province;
import com.tam.StudentManagement.Repository.DistrictRepository;
import com.tam.StudentManagement.Repository.ProvinceRepository;
import com.tam.StudentManagement.Request.District.CreateDistrictRequest;
import com.tam.StudentManagement.Request.District.UpdateDistrictRequest;
import com.tam.StudentManagement.Service.Interface.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<DistrictDto> getAllDistrictsByProvinceId(Integer id) {
        List<District> districts = districtRepository.findAll().stream()
                .filter(x -> x.getIsDelete() == false && x.getProvince().getId().equals(id))
                .collect(Collectors.toList());
        return districts.stream()
                .map(DistrictDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DistrictDto> getDistrictById(Integer id) {
        return districtRepository.findById(id)
                .filter(x -> x.getIsDelete() == false)
                .map(DistrictDto::new);
    }

    @Override
    public CreateDistrictDto createDistrict(CreateDistrictRequest request) {
        // Check for duplicate code
        District existingDistrict = districtRepository.findByCode(request.getCode());
        if (existingDistrict != null) {
            throw new DuplicateException("District code already exists");
        }

        // Check if province exists
        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new NotFoundException("Province not found"));

        District entity = new District();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setProvince(province);

        districtRepository.save(entity);

        return new CreateDistrictDto(entity);
    }

    @Override
    public District updateDistrict(Integer id, UpdateDistrictRequest request) {
        return districtRepository.findById(id).map(district -> {
            // Check for duplicate code if code is being updated
            if (request.getCode() != null && !request.getCode().equals(district.getCode())) {
                District existingDistrict = districtRepository.findByCode(request.getCode());
                if (existingDistrict != null) {
                    throw new DuplicateException("District code already exists");
                }
                district.setCode(request.getCode());
            }

            if (request.getName() != null) {
                district.setName(request.getName());
            }

            if (request.getType() != null) {
                district.setType(request.getType());
            }

            if (request.getProvinceId() != null) {
                Province province = provinceRepository.findById(request.getProvinceId())
                        .orElseThrow(() -> new NotFoundException("Province not found"));
                district.setProvince(province);
            }

            return districtRepository.save(district);
        }).orElseThrow(() -> new NotFoundException("District not found"));
    }

    @Override
    public String deleteDistrict(Integer id) {
        District entity = districtRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("District not found with id: " + id));
        districtRepository.deleteById(id);
        return "District deleted successfully";
    }

    @Override
    public PaginationDto<DistrictDto> getDistrictsByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<District> districtPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            districtPage = districtRepository.findByCodeContainingOrNameContaining(keyword, keyword, pageable);
        } else {
            districtPage = districtRepository.findAll(pageable);
        }

        List<DistrictDto> districtDtos = districtPage.getContent().stream()
                .map(DistrictDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                districtPage.getTotalElements(),
                districtPage.getTotalPages());

        return new PaginationDto<>(districtDtos, paginationInfo);
    }
}