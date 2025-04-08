package com.tam.StudentManagement.Security;

import com.tam.StudentManagement.Model.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class StudentDetails implements UserDetails {
    private final Student student;

    public StudentDetails(Student student) {
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Nếu isAdmin là true, gán quyền ROLE_ADMIN, ngược lại gán ROLE_USER
        String role = student.getIsAdmin() ? "ROLE_ADMIN" : "ROLE_USER";
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getCode(); // Sử dụng mã sinh viên làm username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return student.getStatus() == 1; // Sinh viên có trạng thái 1 là đang hoạt động
    }

    public Student getStudent() {
        return student;
    }
}