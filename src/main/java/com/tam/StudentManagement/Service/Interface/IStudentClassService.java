package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.StudentClass.StudentClassDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.StudentClass.CreateStudentClassDto;
import com.tam.StudentManagement.Model.StudentClass;
import com.tam.StudentManagement.Request.StudentClass.CreateStudentClassRequest;
import com.tam.StudentManagement.Request.StudentClass.UpdateStudentClassRequest;

import java.util.List;
import java.util.Optional;

public interface IStudentClassService {
    List<StudentClass> getAllStudentClasses();

    Optional<StudentClass> getStudentClassById(Integer id);

    CreateStudentClassDto createStudentClass(CreateStudentClassRequest request);

    StudentClass updateStudentClass(Integer id, UpdateStudentClassRequest request);

    String deleteStudentClass(Integer id);

    PaginationDto<StudentClassDto> getStudentClassesByPagination(int pageNumber, int pageSize, String keyword);
}