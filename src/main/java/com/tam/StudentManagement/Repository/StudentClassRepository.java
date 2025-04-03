package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.StudentClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {
    StudentClass findByCode(String code);

    Page<StudentClass> findByCodeContainingOrFullNameContaining(String code, String fullName, Pageable pageable);
}