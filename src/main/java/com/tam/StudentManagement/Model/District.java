package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "districts")
public class District extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String code;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Ward> wards;
    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Student> students;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Dormitory> dormitories;
}