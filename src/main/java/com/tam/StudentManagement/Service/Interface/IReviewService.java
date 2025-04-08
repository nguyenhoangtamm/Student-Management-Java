package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Review.ReviewDto;
import com.tam.StudentManagement.Dto.Review.CreateReviewDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Model.Review;
import com.tam.StudentManagement.Request.Review.CreateReviewRequest;
import com.tam.StudentManagement.Request.Review.UpdateReviewRequest;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
    List<Review> getAllReviews();

    Optional<Review> getReviewById(Integer id);

    CreateReviewDto createReview(CreateReviewRequest request);

    Review updateReview(Integer id, UpdateReviewRequest request);

    String deleteReview(Integer id);

    PaginationDto<ReviewDto> getReviewsByPagination(int pageNumber, int pageSize, String keyword);

    List<Review> getReviewsByDormitoryId(Integer dormitoryId);

    List<Review> getReviewsByStudentId(Integer studentId);
}