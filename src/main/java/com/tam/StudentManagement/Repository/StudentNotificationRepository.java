package com.tam.StudentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tam.StudentManagement.Model.StudentNotification;

// This will be AUTO IMPLEMENTED by Spring into a Bean called studentNotificationRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface StudentNotificationRepository extends JpaRepository<StudentNotification, Integer> {
}