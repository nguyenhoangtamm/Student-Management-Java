package com.tam.StudentManagement.Repository;

import com.tam.StudentManagement.Model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Page<Notification> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword,
            Pageable pageable);
}