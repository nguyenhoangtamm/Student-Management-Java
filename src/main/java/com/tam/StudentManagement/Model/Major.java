package com.tam.StudentManagement.Model;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "majors")
@Data
@EqualsAndHashCode(callSuper = true)
public class Major extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "students", nullable = false)
    private Integer totalStudent = 0;
    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Student> students;

}