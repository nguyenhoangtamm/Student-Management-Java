package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.StudentClass.StudentClassDto;
import com.tam.StudentManagement.Dto.StudentClass.CreateStudentClassDto;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Model.StudentClass;
import com.tam.StudentManagement.Repository.StudentClassRepository;
import com.tam.StudentManagement.Request.StudentClass.CreateStudentClassRequest;
import com.tam.StudentManagement.Request.StudentClass.UpdateStudentClassRequest;
import com.tam.StudentManagement.Service.Interface.IStudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentClassService implements IStudentClassService {
    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public List<StudentClass> getAllStudentClasses() {
        return studentClassRepository.findAll();
    }

    @Override
    public Optional<StudentClass> getStudentClassById(Integer id) {
        return studentClassRepository.findById(id);
    }

    @Override
    public CreateStudentClassDto createStudentClass(CreateStudentClassRequest request) {
        // Check for duplicate code
        StudentClass existingClass = studentClassRepository.findByCode(request.getCode());
        if (existingClass != null) {
            throw new DuplicateException("Student class code already exists");
        }

        StudentClass entity = new StudentClass();
        entity.setCode(request.getCode());
        entity.setFullName(request.getFullName());
        entity.setTotalStudent(0);

        studentClassRepository.save(entity);

        return new CreateStudentClassDto(entity);
    }

    @Override
    public StudentClass updateStudentClass(Integer id, UpdateStudentClassRequest request) {
        return studentClassRepository.findById(id).map(studentClass -> {
            // Check for duplicate code if code is being updated
            if (request.getCode() != null && !request.getCode().equals(studentClass.getCode())) {
                StudentClass existingClass = studentClassRepository.findByCode(request.getCode());
                if (existingClass != null) {
                    throw new DuplicateException("Student class code already exists");
                }
                studentClass.setCode(request.getCode());
            }

            if (request.getFullName() != null) {
                studentClass.setFullName(request.getFullName());
            }

            return studentClassRepository.save(studentClass);
        }).orElseThrow(() -> new RuntimeException("Student class not found"));
    }

    @Override
    public String deleteStudentClass(Integer id) {
        StudentClass entity = studentClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student class not found with id: " + id));
        studentClassRepository.deleteById(id);
        return "Student class deleted successfully";
    }

    @Override
    public PaginationDto<StudentClassDto> getStudentClassesByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<StudentClass> studentClassPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            studentClassPage = studentClassRepository.findByCodeContainingOrFullNameContaining(keyword, keyword,
                    pageable);
        } else {
            studentClassPage = studentClassRepository.findAll(pageable);
        }

        List<StudentClassDto> studentClassDtos = studentClassPage.getContent().stream()
                .map(StudentClassDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                studentClassPage.getTotalElements(),
                studentClassPage.getTotalPages());

        return new PaginationDto<>(studentClassDtos, paginationInfo);
    }
}