package com.tam.StudentManagement.Request.Student;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateStudentRequest {

    @NotBlank(message = "Mã sinh viên không được để trống")
    @Size(max = 30, message = "Mã sinh viên tối đa 30 ký tự")
    private String code;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @NotNull(message = "Giới tính không được để trống")
    @Min(value = 0, message = "Giới tính chỉ được nhập 0 hoặc 1")
    @Max(value = 1, message = "Giới tính chỉ được nhập 0 hoặc 1")

    private Integer gender;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dateOfBirth;

    private String faculty = "Unknown";

    private Integer dormitoryId;

    private String room;

    @NotNull(message = "Mã lớp không được để trống")
    private Integer classId;

    @NotNull(message = "Mã ngành không được để trống")
    private Integer majorId;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 30, message = "Số điện thoại tối đa 30 ký tự")
    private String phoneNumber;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    private Integer educationLevel;

    private Integer residenceStatus = 1;

    @NotBlank(message = "Năm học không được để trống")
    private String academicYear = "2025";

    @NotBlank(message = "Nơi sinh không được để trống")
    private String birthplace;

    private Integer status = 0;

    private Boolean isAdmin = false;

    private String avatar;

    private BigDecimal monthlyRent;

    private Integer contractStatus;

    private String address;

    private String fullAddress;

    private Integer wardId;

    private Integer districtId;

    private Integer provinceId;
}
