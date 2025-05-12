package com.tam.StudentManagement.Dto.Dormitory;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.tam.StudentManagement.Model.Dormitory;

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
    private String rating;
    private Integer students;
    private String slug;
    private String minPrice;
    private String maxPrice;
    private String longitude;
    private String latitude;
    private Integer status;

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

        this.rating = entity.getRating().toString();
        this.students = 12;
        this.slug = entity.getSlug();
        this.minPrice = entity.getMinPrice().toString();
        this.maxPrice = entity.getMaxPrice().toString();
        this.longitude = entity.getLongitude().toString();
        this.latitude = entity.getLatitude().toString();
        this.status = entity.getStatus();
    }
}