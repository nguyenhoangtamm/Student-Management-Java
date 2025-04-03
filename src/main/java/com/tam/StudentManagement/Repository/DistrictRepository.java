package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    District findByCode(String code);

    Page<District> findByCodeContainingOrNameContaining(String code, String name, Pageable pageable);
}