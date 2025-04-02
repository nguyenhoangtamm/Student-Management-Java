package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "dormitories")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(name = "full_address", columnDefinition = "TEXT")
    private String fullAddress;

    @Column(name = "ward_id", insertable = false, updatable = false)
    private Integer wardId;

    @Column(name = "district_id", insertable = false, updatable = false)
    private Integer districtId;

    @Column(name = "province_id", insertable = false, updatable = false)
    private Integer provinceId;

    @Column(name = "owner_name", length = 50)
    private String ownerName;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "review_count")
    private Integer reviewCount;

    private Integer rooms;

    private Integer contacts;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "student_count")
    private Integer studentCount;

    @Column(length = 255)
    private String slug;

    @Column(name = "min_price", precision = 10, scale = 2)
    private BigDecimal minPrice;

    @Column(name = "max_price", precision = 10, scale = 2)
    private BigDecimal maxPrice;

    private Float longitude;

    private Float latitude;

    @OneToMany(mappedBy = "dormitory")
    @JsonIgnore
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    @JsonIgnore
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private District district;

    @ManyToOne
    @JoinColumn(name = "province_id")
    @JsonIgnore
    private Province province;
}
