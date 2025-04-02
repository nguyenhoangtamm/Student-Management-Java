package com.tam.StudentManagement.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")
public class Province extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String code;

    @Column(length = 255)
    private String name;

    @Column(name = "type")
    private Integer type;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<District> districts;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<Student> students;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<Dormitory> dormitories;
}
