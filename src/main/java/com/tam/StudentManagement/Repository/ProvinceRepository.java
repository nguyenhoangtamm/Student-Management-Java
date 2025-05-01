package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    Province findByCode(String code);
    Page<Province> findByCodeContainingOrNameContaining(String code, String name, Pageable pageable);
}