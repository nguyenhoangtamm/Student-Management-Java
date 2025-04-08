package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Auth.LoginDto;
import com.tam.StudentManagement.Dto.Auth.JwtAuthResponse;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Repository.StudentRepository;
import com.tam.StudentManagement.Security.JwtTokenProvider;
import com.tam.StudentManagement.Security.StudentDetails;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getCode(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        // Lấy thông tin sinh viên
        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        Student student = studentDetails.getStudent();

        return new JwtAuthResponse(
                token,
                "Bearer",
                student.getId(),
                student.getCode(),
                student.getFullName(),
                student.getIsAdmin());
    }
}