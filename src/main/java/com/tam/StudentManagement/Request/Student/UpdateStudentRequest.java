
package com.tam.StudentManagement.Request.Student;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class UpdateStudentRequest {

     @NotBlank(message = "Mã sinh viên là bắt buộc")
    @Size(max = 255, message = "Mã sinh viên tối đa 255 ký tự")
    private String code;

    @NotBlank(message = "Họ và tên là bắt buộc")
    @Size(max = 255, message = "Họ và tên tối đa 255 ký tự")
    private String fullName;

    @NotNull(message = "Giới tính là bắt buộc")
    @Min(value = 0, message = "Giới tính phải lớn hơn hoặc bằng 0")
    @Max(value = 2, message = "Giới tính phải nhỏ hơn hoặc bằng 2")
    private Integer gender;

    @NotNull(message = "Ngày sinh là bắt buộc")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Số điện thoại là bắt buộc")
    @Size(max = 15, message = "Số điện thoại tối đa 15 ký tự")
    private String phoneNumber;

    @Email(message = "Định dạng email không hợp lệ")
    @NotBlank(message = "Email là bắt buộc")
    @Size(max = 255, message = "Email tối đa 255 ký tự")
    private String email;

    @NotNull(message = "ID lớp là bắt buộc")
    @PositiveOrZero(message = "ID lớp phải là số không âm")
    private Integer classId;

    @NotNull(message = "ID ngành là bắt buộc")
    @PositiveOrZero(message = "ID ngành phải là số không âm")
    private Integer majorId;

    @NotBlank(message = "Năm học là bắt buộc")
    @Size(max = 9, message = "Năm học tối đa 9 ký tự")
    private String academicYear;

    @PositiveOrZero(message = "ID tỉnh phải là số không âm")
    private Integer provinceId;
}
