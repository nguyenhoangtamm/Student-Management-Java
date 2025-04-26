package com.tam.StudentManagement.Dto.Dormitory;

import com.tam.StudentManagement.Model.Review;

import lombok.Data;
@Data
public class DormitoryReviewDto {

    private int id;
    private String name;
    private String comment;
    private String rating;
    private String date;

    public DormitoryReviewDto(Review review) {
        this.id = review.getId();
        this.name = review.getStudent().getFullName();
        this.comment = review.getComment();
        this.rating = review.getRating().toString();
        this.date = review.getCreatedAt().toString();
    }
}
