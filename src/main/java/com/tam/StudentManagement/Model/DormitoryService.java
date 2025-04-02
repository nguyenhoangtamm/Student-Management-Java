package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dormitory_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryService extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dormitory_id", insertable = false, updatable = false)
    private Integer dormitoryId;

    @Column(name = "service_id", insertable = false, updatable = false)
    private Integer serviceId;

    private Integer fee;

    @ManyToOne
    @JoinColumn(name = "dormitory_id")
    private Dormitory dormitory;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}