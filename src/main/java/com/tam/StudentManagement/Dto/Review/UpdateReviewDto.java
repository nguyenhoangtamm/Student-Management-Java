package com.tam.StudentManagement.Dto.Review;

import lombok.Data;
import com.tam.StudentManagement.Model.Review;
import java.math.BigDecimal;

@Data
public class UpdateReviewDto {
    private Integer id;
    private String comment;
    private BigDecimal rating;
    private Integer studentId;
    private Integer dormitoryId;

    public UpdateReviewDto(Review entity) {
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.rating = entity.getRating();
        this.studentId = entity.getStudent().getId();
        this.dormitoryId = entity.getDormitory().getId();
    }
}