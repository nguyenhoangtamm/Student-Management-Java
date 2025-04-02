package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wards")
public class Ward extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String code;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private District district;

    @OneToMany(mappedBy = "ward")
    @JsonIgnore
    private List<Student> students;

    @OneToMany(mappedBy = "ward")
    @JsonIgnore
    private List<Dormitory> dormitories;
}