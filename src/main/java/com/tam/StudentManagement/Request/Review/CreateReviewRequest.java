package com.tam.StudentManagement.Request.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CreateReviewRequest {
    @NotBlank(message = "Content is required")
    private String comment;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

    @NotBlank(message = "Student id is required")
    private Integer studentId;

    @NotBlank(message = "Dormitory id is required")
    private Integer dormitoryId;
} 