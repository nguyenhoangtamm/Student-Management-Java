package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Review;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Model.Dormitory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByCommentContaining(String comment, Pageable pageable);

    List<Review> findByStudent(Student student);

    List<Review> findByDormitory(Dormitory dormitory);
}