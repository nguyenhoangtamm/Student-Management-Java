package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Dormitory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
    Dormitory findByName(String name);

    Dormitory findBySlug(String slug);

    Page<Dormitory> findByNameContainingOrAddressContainingOrDescriptionContaining(
            String name, String address, String description, Pageable pageable);
}