package com.tam.StudentManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(nullable = false)
    private Short gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = true, length = 255)
    private String faculty = "Unknown";

    @Column(name = "dormitory_id", insertable = false, updatable = false)
    private Integer dormitoryId;

    @Column(length = 30)
    private String room;

    @Column(name = "class_id", nullable = false)
    private Integer classId;

    @Column(name = "major_id", nullable = false)
    private Integer majorId;

    @Column(name = "phone_number", nullable = false, unique = true, length = 30)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "education_level")
    private Short educationLevel;

    @Column(name = "residence_status", nullable = false)
    private Integer residenceStatus = 1;

    @Column(name = "academic_year", nullable = false, length = 30)
    private String academicYear = "2025";

    @Column(nullable = false, length = 255)
    private String birthplace;

    @Column(nullable = false)
    private Short status = 0;

    @Column(name = "isadmin", nullable = false)
    private Boolean isAdmin = false;

    private String avatar;

    @Column(name = "monthly_rent", precision = 10, scale = 2)
    private BigDecimal monthlyRent;

    @Column(name = "contract_status")
    private Short contractStatus;

    private String address;

    @Column(name = "full_address", columnDefinition = "TEXT")
    private String fullAddress;

    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "province_id")
    private Integer provinceId;
    @ManyToOne
    @JoinColumn(name = "dormitory_id", referencedColumnName = "id")
    @JsonIgnore

    private Dormitory dormitory;





}
