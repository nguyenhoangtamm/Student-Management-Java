package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Dormitory.DormitoryDto;
import com.tam.StudentManagement.Dto.Dormitory.CreateDormitoryDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Model.Dormitory;
import com.tam.StudentManagement.Model.Ward;
import com.tam.StudentManagement.Model.District;
import com.tam.StudentManagement.Model.Province;
import com.tam.StudentManagement.Repository.DormitoryRepository;
import com.tam.StudentManagement.Repository.WardRepository;
import com.tam.StudentManagement.Repository.DistrictRepository;
import com.tam.StudentManagement.Repository.ProvinceRepository;
import com.tam.StudentManagement.Request.Dormitory.CreateDormitoryRequest;
import com.tam.StudentManagement.Request.Dormitory.UpdateDormitoryRequest;
import com.tam.StudentManagement.Service.Interface.IDormitoryService;
import com.tam.StudentManagement.Util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DormitoryService implements IDormitoryService {
    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Dormitory> getAllDormitories() {
        return dormitoryRepository.findAll();
    }

    @Override
    public Optional<Dormitory> getDormitoryById(Integer id) {
        return dormitoryRepository.findById(id);
    }

    @Override
    public CreateDormitoryDto createDormitory(CreateDormitoryRequest request) {
        // Check for duplicate name
        Dormitory existingDormitory = dormitoryRepository.findByName(request.getName());
        if (existingDormitory != null) {
            throw new DuplicateException("Dormitory name already exists");
        }

        Dormitory entity = new Dormitory();
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setFullAddress(request.getFullAddress());
        entity.setOwnerName(request.getOwnerName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setDescription(request.getDescription());
        entity.setContent(request.getContent());
        entity.setRooms(request.getRooms());
        entity.setMinPrice(request.getMinPrice());
        entity.setMaxPrice(request.getMaxPrice());
        entity.setLongitude(request.getLongitude());
        entity.setLatitude(request.getLatitude());

        // Set relationships
        if (request.getWardId() != null) {
            Optional<Ward> ward = wardRepository.findById(request.getWardId());
            ward.ifPresent(entity::setWard);
        }

        if (request.getDistrictId() != null) {
            Optional<District> district = districtRepository.findById(request.getDistrictId());
            district.ifPresent(entity::setDistrict);
        }

        if (request.getProvinceId() != null) {
            Optional<Province> province = provinceRepository.findById(request.getProvinceId());
            province.ifPresent(entity::setProvince);
        }

        // Generate slug
        String slug = SlugUtil.generateUniqueSlug(request.getName(),
                existingSlug -> dormitoryRepository.findBySlug(existingSlug) != null);
        entity.setSlug(slug);

        // Set initial values
        entity.setReviewCount(0);
        entity.setContacts(0);
        entity.setRating(BigDecimal.ZERO);
        entity.setStudentCount(0);

        dormitoryRepository.save(entity);

        return new CreateDormitoryDto(entity);
    }

    @Override
    public Dormitory updateDormitory(Integer id, UpdateDormitoryRequest request) {
        return dormitoryRepository.findById(id).map(dormitory -> {
            // Check for duplicate name if name is being updated
            if (request.getName() != null && !request.getName().equals(dormitory.getName())) {
                Dormitory existingDormitory = dormitoryRepository.findByName(request.getName());
                if (existingDormitory != null) {
                    throw new DuplicateException("Dormitory name already exists");
                }
                dormitory.setName(request.getName());

                // Update slug if name changes
                String slug = SlugUtil.generateUniqueSlug(request.getName(),
                        existingSlug -> dormitoryRepository.findBySlug(existingSlug) != null);
                dormitory.setSlug(slug);
            }

            if (request.getAddress() != null) {
                dormitory.setAddress(request.getAddress());
            }
            if (request.getFullAddress() != null) {
                dormitory.setFullAddress(request.getFullAddress());
            }
            if (request.getOwnerName() != null) {
                dormitory.setOwnerName(request.getOwnerName());
            }
            if (request.getPhoneNumber() != null) {
                dormitory.setPhoneNumber(request.getPhoneNumber());
            }
            if (request.getDescription() != null) {
                dormitory.setDescription(request.getDescription());
            }
            if (request.getContent() != null) {
                dormitory.setContent(request.getContent());
            }
            if (request.getRooms() != null) {
                dormitory.setRooms(request.getRooms());
            }
            if (request.getMinPrice() != null) {
                dormitory.setMinPrice(request.getMinPrice());
            }
            if (request.getMaxPrice() != null) {
                dormitory.setMaxPrice(request.getMaxPrice());
            }
            if (request.getLongitude() != null) {
                dormitory.setLongitude(request.getLongitude());
            }
            if (request.getLatitude() != null) {
                dormitory.setLatitude(request.getLatitude());
            }

            // Update relationships
            if (request.getWardId() != null) {
                Optional<Ward> ward = wardRepository.findById(request.getWardId());
                ward.ifPresent(dormitory::setWard);
            }

            if (request.getDistrictId() != null) {
                Optional<District> district = districtRepository.findById(request.getDistrictId());
                district.ifPresent(dormitory::setDistrict);
            }

            if (request.getProvinceId() != null) {
                Optional<Province> province = provinceRepository.findById(request.getProvinceId());
                province.ifPresent(dormitory::setProvince);
            }

            return dormitoryRepository.save(dormitory);
        }).orElseThrow(() -> new RuntimeException("Dormitory not found"));
    }

    @Override
    public String deleteDormitory(Integer id) {
        Dormitory entity = dormitoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dormitory not found with id: " + id));
        dormitoryRepository.deleteById(id);
        return "Dormitory deleted successfully";
    }

    @Override
    public PaginationDto<DormitoryDto> getDormitoriesByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Dormitory> dormitoryPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            dormitoryPage = dormitoryRepository
                    .findByNameContainingOrAddressContainingOrDescriptionContaining(
                            keyword, keyword, keyword, pageable);
        } else {
            dormitoryPage = dormitoryRepository.findAll(pageable);
        }

        List<DormitoryDto> dormitoryDtos = dormitoryPage.getContent().stream()
                .map(DormitoryDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                dormitoryPage.getTotalElements(),
                dormitoryPage.getTotalPages());

        return new PaginationDto<DormitoryDto>(dormitoryDtos, paginationInfo);
    }
}