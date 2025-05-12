package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Auth.LoginDto;
import com.tam.StudentManagement.Dto.Auth.UserLogin;
import com.tam.StudentManagement.Dto.Auth.JwtAuthResponse;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Security.JwtTokenProvider;
import com.tam.StudentManagement.Security.StudentDetails;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtTokenProvider tokenProvider;

        @Override
        public JwtAuthResponse login(LoginDto loginDto, HttpServletResponse response) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                loginDto.getCode(),
                                                loginDto.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = tokenProvider.generateToken(authentication);

                ResponseCookie cookie = ResponseCookie.from("auth_token", token)
                                .httpOnly(true) // Ngăn JS đọc
                                .secure(false) // Bật true nếu dùng HTTPS
                                .path("/")
                                .maxAge(7 * 24 * 60 * 60) // 7 ngày
                                .sameSite("Lax") // Tránh lỗi CORS
                                .build();

                // 👇 Set cookie vào response
                response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

                // Lấy thông tin sinh viên
                StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
                Student student = studentDetails.getStudent();
                UserLogin userLogin = new UserLogin(
                                student.getCode(),
                                student.getIsAdmin());

                return new JwtAuthResponse(
                                true,
                                token,
                                userLogin);
        }

        public void logout(HttpServletResponse response) {
                ResponseCookie cookie = ResponseCookie.from("auth_token", null)
                                .httpOnly(true)
                                .secure(false) // Bật true nếu dùng HTTPS
                                .path("/")
                                .maxAge(0) // Đặt maxAge về 0 để xóa cookie
                                .sameSite("Lax")
                                .build();

                response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

}