package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
}