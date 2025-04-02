package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Student.StudentDto;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Request.Student.CreateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateStudentRequest;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Integer id);
    StudentDto createStudent(CreateStudentRequest student);
    Student updateStudent(Integer id, UpdateStudentRequest studentDetails);
    String deleteStudent(Integer id);
    String getMajorByStudentId(Integer id);
}
