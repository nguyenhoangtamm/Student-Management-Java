package com.tam.StudentManagement.Model;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "student_classes")
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentClass extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String fullName;
    @Column(name = "students", nullable = false, unique = true)
    private Integer totalStudent;


    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Student> students;
}
