package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Regulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Integer> {
    Page<Regulation> findByNameContaining(String name, Pageable pageable);
} 