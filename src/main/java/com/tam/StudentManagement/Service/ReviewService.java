package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Review.ReviewDto;
import com.tam.StudentManagement.Dto.Review.CreateReviewDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.NotFoundException;
import com.tam.StudentManagement.Model.Review;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Model.Dormitory;
import com.tam.StudentManagement.Repository.ReviewRepository;
import com.tam.StudentManagement.Repository.StudentRepository;
import com.tam.StudentManagement.Repository.DormitoryRepository;
import com.tam.StudentManagement.Request.Review.CreateReviewRequest;
import com.tam.StudentManagement.Request.Review.UpdateReviewRequest;
import com.tam.StudentManagement.Service.Interface.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(Integer id) {
        return reviewRepository.findById(id);
    }

    @Override
    public CreateReviewDto createReview(CreateReviewRequest request) {
        // Check if student exists
        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new NotFoundException("Student not found"));
        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        // Check if dormitory exists
        Dormitory dormitory = dormitoryRepository.findById(request.getDormitoryId()).orElseThrow(() -> new NotFoundException("Dormitory not found"));
        if (dormitory == null) {
            throw new NotFoundException("Dormitory not found");
        }

        Review entity = new Review();
        entity.setComment(request.getComment());
        entity.setRating(BigDecimal.valueOf(request.getRating()));
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStudent(student);
        entity.setDormitory(dormitory);

        reviewRepository.save(entity);

        return new CreateReviewDto(entity);
    }

    @Override
    public Review updateReview(Integer id, UpdateReviewRequest request) {
        return reviewRepository.findById(id).map(review -> {
            if (request.getComment() != null) {
                review.setComment(request.getComment());
            }

            if (request.getRating() != null) {
                review.setRating(BigDecimal.valueOf(request.getRating()));
            }

            return reviewRepository.save(review);
        }).orElseThrow(() -> new NotFoundException("Review not found"));
    }

    @Override
    public String deleteReview(Integer id) {
        Review entity = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found with id: " + id));
        reviewRepository.deleteById(id);
        return "Review deleted successfully";
    }

    @Override
    public PaginationDto<ReviewDto> getReviewsByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Review> reviewPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            reviewPage = reviewRepository.findByCommentContaining(keyword, pageable);
        } else {
            reviewPage = reviewRepository.findAll(pageable);
        }

        List<ReviewDto> reviewDtos = reviewPage.getContent().stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                reviewPage.getTotalElements(),
                reviewPage.getTotalPages());

        return new PaginationDto<>(reviewDtos, paginationInfo);
    }

    @Override
    public List<Review> getReviewsByDormitoryId(Integer dormitoryId) {
        Dormitory dormitory = dormitoryRepository.findById(dormitoryId).orElseThrow(() -> new NotFoundException("Dormitory not found"));
        return reviewRepository.findByDormitory(dormitory);
    }

    @Override
    public List<Review> getReviewsByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));
        return reviewRepository.findByStudent(student);
    }
}