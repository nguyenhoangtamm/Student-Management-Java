package com.tam.StudentManagement.Dto.Dormitory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tam.StudentManagement.Model.Dormitory;

import lombok.Data;

@Data
public class GetDormitoryBySlug {

    private int id;
    private String name;
    private String address;

    @JsonProperty("full_address")
    private String fullAddress;

    @JsonProperty("ward_id")
    private int wardId;

    @JsonProperty("district_id")
    private int districtId;

    @JsonProperty("province_id")
    private int provinceId;

    @JsonProperty("owner_name")
    private String ownerName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String description;
    private String content;
    private int reviews;
    private int rooms;
    private String rating;
    private int students;

    @JsonProperty("min_price")
    private String minPrice;

    @JsonProperty("max_price")
    private String maxPrice;

    private String longitude;
    private String latitude;
    private String status;
    public GetDormitoryBySlug(Dormitory entity) {
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
        this.reviews = entity.getReviewCount();
        this.rooms = entity.getRooms();
        this.rating = entity.getRating().toString();
        this.students = entity.getStudentCount();
        this.minPrice = entity.getMinPrice().toString();
        this.maxPrice = entity.getMaxPrice().toString();
        this.longitude = entity.getLongitude().toString();
        this.latitude = entity.getLatitude().toString();
        this.status = entity.getStatus().toString();
    }
}
