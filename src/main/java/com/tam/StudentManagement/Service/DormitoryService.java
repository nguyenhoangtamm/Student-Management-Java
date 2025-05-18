package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Dormitory.DormitoryDto;
import com.tam.StudentManagement.Dto.Dormitory.DormitoryPaginationDto;
import com.tam.StudentManagement.Dto.Dormitory.DormitoryReviewDto;
import com.tam.StudentManagement.Dto.Dormitory.GetDormitoryBySlug;
import com.tam.StudentManagement.Dto.Dormitory.CreateDormitoryDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Model.Dormitory;
import com.tam.StudentManagement.Model.Ward;
import com.tam.StudentManagement.Model.District;
import com.tam.StudentManagement.Model.Province;
import com.tam.StudentManagement.Model.Review;
import com.tam.StudentManagement.Repository.DormitoryRepository;
import com.tam.StudentManagement.Repository.FileRepository;
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
    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<Dormitory> getAllDormitories() {
        return dormitoryRepository.findAll().stream()
                .filter(dormitory -> !dormitory.getIsDelete())
                .collect(Collectors.toList());
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

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Dormitory name is required");
        }
        if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Dormitory address is required");
        }
        if (request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        Dormitory entity = new Dormitory();
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setStatus(request.getStatus());
        entity.setOwnerName(request.getOwnerName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setDescription(request.getDescription());
        entity.setContent(request.getContent());
        entity.setRooms(request.getRooms());
        entity.setMinPrice(request.getMinPrice());
        entity.setMaxPrice(request.getMaxPrice());
        entity.setLongitude(request.getLongitude());
        entity.setLatitude(request.getLatitude());

        entity.setRooms(request.getRooms() != null ? request.getRooms() : 0);
        entity.setMinPrice(request.getMinPrice() != null ? request.getMinPrice() : BigDecimal.ZERO);
        entity.setMaxPrice(request.getMaxPrice() != null ? request.getMaxPrice() : BigDecimal.ZERO);
        entity.setLongitude(request.getLongitude() != null ? request.getLongitude() : 0.0f);
        entity.setLatitude(request.getLatitude() != null ? request.getLatitude() : 0.0f);
        String fullAdress = request.getAddress() + ", " +
                (request.getWardId() != null ? wardRepository.findById(request.getWardId()).get().getName() : "") + ", "
                +
                (request.getDistrictId() != null ? districtRepository.findById(request.getDistrictId()).get().getName()
                        : "")
                + ", " +
                (request.getProvinceId() != null ? provinceRepository.findById(request.getProvinceId()).get().getName()
                        : "");
        entity.setFullAddress(fullAdress);

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
        Dormitory dormitory = dormitoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dormitory not found with id: " + id));

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

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Dormitory name is required");
        }
        if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Dormitory address is required");
        }
        if (request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        if (request.getMinPrice() != null && request.getMaxPrice() != null &&
                request.getMinPrice().compareTo(request.getMaxPrice()) > 0) {
            throw new IllegalArgumentException("Invalid price range");
        }

        dormitory.setAddress(request.getAddress());
        dormitory.setOwnerName(request.getOwnerName());
        dormitory.setPhoneNumber(request.getPhoneNumber());
        dormitory.setDescription(request.getDescription());
        dormitory.setContent(request.getContent());
        dormitory.setRooms(request.getRooms() != null ? request.getRooms() : dormitory.getRooms());
        dormitory.setMinPrice(request.getMinPrice() != null ? request.getMinPrice() : dormitory.getMinPrice());
        dormitory.setMaxPrice(request.getMaxPrice() != null ? request.getMaxPrice() : dormitory.getMaxPrice());
        dormitory.setLongitude(request.getLongitude() != null ? request.getLongitude() : dormitory.getLongitude());
        dormitory.setLatitude(request.getLatitude() != null ? request.getLatitude() : dormitory.getLatitude());

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

        String fullAddress = request.getAddress() + ", " +
                (request.getWardId() != null ? wardRepository.findById(request.getWardId()).get().getName() : "") + ", "
                +
                (request.getDistrictId() != null ? districtRepository.findById(request.getDistrictId()).get().getName()
                        : "")
                + ", " +
                (request.getProvinceId() != null ? provinceRepository.findById(request.getProvinceId()).get().getName()
                        : "");
        dormitory.setFullAddress(fullAddress);

        return dormitoryRepository.save(dormitory);
    }

    @Override
    public String deleteDormitory(Integer id) {
        Dormitory entity = dormitoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dormitory not found with id: " + id));
        entity.setIsDelete(true);
        dormitoryRepository.save(entity);
        return "Dormitory marked as deleted successfully";
    }

    @Override
    public PaginationDto<DormitoryPaginationDto> getDormitoriesByPagination(int pageNumber, int pageSize,
            String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Dormitory> dormitoryPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            dormitoryPage = dormitoryRepository
                    .findByNameContainingOrAddressContainingOrDescriptionContaining(
                            keyword, keyword, keyword, pageable);
        } else {
            dormitoryPage = dormitoryRepository.findAll(pageable);
        }

        List<DormitoryPaginationDto> dormitoryDtos = new java.util.ArrayList<>();
        for (Dormitory dormitory : dormitoryPage.getContent()) {
            if (!dormitory.getIsDelete()) {

                String imageUrl = fileRepository.findAll().stream()
                        .filter(file -> file.getFileableType().equals("dormitory")
                                && file.getFileableId().equals(dormitory.getId()))
                        .map(file -> file.getPath())
                        .findFirst()
                        .orElse(null);
                dormitoryDtos.add(new DormitoryPaginationDto(dormitory, imageUrl));
            }
        }
        dormitoryDtos.sort((d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName()));

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                dormitoryPage.getTotalPages(),
                dormitoryPage.getTotalPages());

        return new PaginationDto<DormitoryPaginationDto>(dormitoryDtos, paginationInfo);
    }

    public GetDormitoryBySlug getDormitoryBySlug(String slug) {
        Dormitory dormitory = dormitoryRepository.findBySlug(slug);

        if (dormitory == null) {
            throw new RuntimeException("Dormitory not found with slug: " + slug);
        }
        String imageUrl = fileRepository.findAll().stream()
                .filter(file -> file.getFileableType().equals("dormitory")
                        && file.getFileableId().equals(dormitory.getId()))
                .map(file -> file.getPath())
                .findFirst()
                .orElse(null);
        GetDormitoryBySlug getSlug = new GetDormitoryBySlug(dormitory, imageUrl);
        return getSlug;
    }

    public List<DormitoryReviewDto> getDormitoryReviewById(int id) {
        Dormitory dormitory = dormitoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dormitory not found with id: " + id));
        List<Review> reviews = dormitory.getReviews();

        List<DormitoryReviewDto> dormitoryReviewDtos = reviews.stream()
                .map(DormitoryReviewDto::new)
                .collect(Collectors.toList());
        return dormitoryReviewDtos;

    }
}