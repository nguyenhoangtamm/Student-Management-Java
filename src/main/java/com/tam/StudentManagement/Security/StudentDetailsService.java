package com.tam.StudentManagement.Security;

import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm sinh viên theo mã sinh viên (username)
        Student student = studentRepository.findByCode(username);

        if (student == null) {
            throw new UsernameNotFoundException("Student not found with code: " + username);
        }

        return new StudentDetails(student);
    }
}