package com.tam.StudentManagement.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tam.StudentManagement.Model.Student;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByCode(String code);

    Student findByEmail(String email);
    int countByResidenceStatus(int residenceStatus);

    Student findByPhoneNumber(String phoneNumber);

    // Tìm kiếm với phân trang
    Page<Student> findByFullNameContaining(String fullName, Pageable pageable);

    Page<Student> findByCodeContaining(String code, Pageable pageable);

    Page<Student> findByEmailContaining(String email, Pageable pageable);

    Page<Student> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);

    Page<Student> findByFullNameContainingOrCodeContainingOrEmailContainingOrPhoneNumberContaining(
            String fullName, String code, String email, String phoneNumber, Pageable pageable);
}