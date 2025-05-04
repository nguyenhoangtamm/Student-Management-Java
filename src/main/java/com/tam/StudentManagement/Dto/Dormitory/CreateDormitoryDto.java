package com.tam.StudentManagement.Dto.Dormitory;

import lombok.Data;
import java.math.BigDecimal;

import com.tam.StudentManagement.Model.Dormitory;

@Data
public class CreateDormitoryDto {
    private String name;
    private String address;
    private Integer wardId;
    private Integer districtId;
    private Integer provinceId;
    private String ownerName;
    private String phoneNumber;
    private String description;
    private String content;
    private Integer status;
    private Integer rooms;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Float longitude;
    private Float latitude;
    public CreateDormitoryDto(Dormitory entity) {
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.status = entity.getStatus();
        this.wardId = entity.getWardId();
        this.districtId = entity.getDistrictId();
        this.provinceId = entity.getProvinceId();
        this.ownerName = entity.getOwnerName();
        this.phoneNumber = entity.getPhoneNumber();
        this.description = entity.getDescription();
        this.content = entity.getContent();
        this.rooms = entity.getRooms();
        this.minPrice = entity.getMinPrice();
        this.maxPrice = entity.getMaxPrice();
        this.longitude = entity.getLongitude();
        this.latitude = entity.getLatitude();
    }
}