package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Student.StudentDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Student.CreateStudentDto;
import com.tam.StudentManagement.Dto.Student.StudentDashboardDto;
import com.tam.StudentManagement.Dto.Student.StudentPaginationResponse;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Request.Student.CreateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateStudentRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();

    Optional<Student> getStudentById(Integer id);

    CreateStudentDto createStudent(CreateStudentRequest student);

    Student updateStudent(Integer id, UpdateStudentRequest studentDetails);

    String deleteStudent(Integer id);

    StudentDashboardDto getDashboard();

    PaginationDto<StudentDto> getStudentsByPagination(int page, int size, String keyword);

}
