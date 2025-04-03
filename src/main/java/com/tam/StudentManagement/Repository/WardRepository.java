package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    Ward findByCode(String code);

    Page<Ward> findByCodeContainingOrNameContaining(String code, String name, Pageable pageable);
}