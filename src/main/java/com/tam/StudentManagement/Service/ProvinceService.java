package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Province.ProvinceDto;
import com.tam.StudentManagement.Dto.Province.CreateProvinceDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Model.Province;
import com.tam.StudentManagement.Repository.ProvinceRepository;
import com.tam.StudentManagement.Request.Province.CreateProvinceRequest;
import com.tam.StudentManagement.Request.Province.UpdateProvinceRequest;
import com.tam.StudentManagement.Service.Interface.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> getProvinceById(Integer id) {
        return provinceRepository.findById(id);
    }

    @Override
    public CreateProvinceDto createProvince(CreateProvinceRequest request) {
        // Check for duplicate code
        Province existingProvince = provinceRepository.findByCode(request.getCode());
        if (existingProvince != null) {
            throw new DuplicateException("Province code already exists");
        }

        Province entity = new Province();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setType(request.getType());

        provinceRepository.save(entity);

        return new CreateProvinceDto(entity);
    }

    @Override
    public Province updateProvince(Integer id, UpdateProvinceRequest request) {
        return provinceRepository.findById(id).map(province -> {
            // Check for duplicate code if code is being updated
            if (request.getCode() != null && !request.getCode().equals(province.getCode())) {
                Province existingProvince = provinceRepository.findByCode(request.getCode());
                if (existingProvince != null) {
                    throw new DuplicateException("Province code already exists");
                }
                province.setCode(request.getCode());
            }

            if (request.getName() != null) {
                province.setName(request.getName());
            }

            if (request.getType() != null) {
                province.setType(request.getType());
            }

            return provinceRepository.save(province);
        }).orElseThrow(() -> new RuntimeException("Province not found"));
    }

    @Override
    public String deleteProvince(Integer id) {
        Province entity = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found with id: " + id));
        provinceRepository.deleteById(id);
        return "Province deleted successfully";
    }

    @Override
    public PaginationDto<ProvinceDto> getProvincesByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Province> provincePage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            provincePage = provinceRepository.findByCodeContainingOrNameContaining(keyword, keyword, pageable);
        } else {
            provincePage = provinceRepository.findAll(pageable);
        }

        List<ProvinceDto> provinceDtos = provincePage.getContent().stream()
                .map(ProvinceDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                provincePage.getTotalElements(),
                provincePage.getTotalPages());

        return new PaginationDto<>(provinceDtos, paginationInfo);
    }
}