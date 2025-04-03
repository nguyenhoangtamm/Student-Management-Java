package com.tam.StudentManagement.Dto.Dormitory;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.Dormitory;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryDto {
    private Integer id;
    private String name;
    private String address;
    private String fullAddress;
    private Integer wardId;
    private Integer districtId;
    private Integer provinceId;
    private String ownerName;
    private String phoneNumber;
    private String description;
    private String content;
    private Integer reviewCount;
    private Integer rooms;
    private Integer contacts;
    private BigDecimal rating;
    private Integer studentCount;
    private String slug;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Float longitude;
    private Float latitude;

    public DormitoryDto(Dormitory entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.fullAddress = entity.getFullAddress();
        this.wardId = entity.getWardId();
        this.districtId = entity.getDistrictId();
        this.provinceId = entity.getProvinceId();
        this.ownerName = entity.getOwnerName();
        this.phoneNumber = entity.getPhoneNumber();
        this.description = entity.getDescription();
        this.content = entity.getContent();
        this.reviewCount = entity.getReviewCount();
        this.rooms = entity.getRooms();
        this.contacts = entity.getContacts();
        this.rating = entity.getRating();
        this.studentCount = entity.getStudentCount();
        this.slug = entity.getSlug();
        this.minPrice = entity.getMinPrice();
        this.maxPrice = entity.getMaxPrice();
        this.longitude = entity.getLongitude();
        this.latitude = entity.getLatitude();
    }
}