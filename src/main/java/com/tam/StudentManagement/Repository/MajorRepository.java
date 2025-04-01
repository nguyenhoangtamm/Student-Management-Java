package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MajorRepository extends JpaRepository<Major, Integer> {

}