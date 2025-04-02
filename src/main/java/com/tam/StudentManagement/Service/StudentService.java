package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Student.StudentDto;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Repository.StudentRepository;
import com.tam.StudentManagement.Request.Student.CreateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateStudentRequest;
import com.tam.StudentManagement.Service.Interface.IStudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public StudentDto createStudent(CreateStudentRequest request) {
        Student entity = new Student();
        entity.setCode(request.getCode());
        entity.setPassword(request.getPassword());
        entity.setFullName(request.getFullName());
        entity.setGender(request.getGender());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setFaculty(request.getFaculty());
        entity.setDormitoryId(request.getDormitoryId());
        entity.setRoom(request.getRoom());
        entity.setClassId(request.getClassId());
        entity.setMajorId(request.getMajorId());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setEmail(request.getEmail());
        entity.setEducationLevel(request.getEducationLevel());
        entity.setResidenceStatus(request.getResidenceStatus());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setBirthplace(request.getBirthplace());
        entity.setStatus(request.getStatus());
        entity.setIsAdmin(request.getIsAdmin());
        entity.setAvatar(request.getAvatar());
        entity.setMonthlyRent(request.getMonthlyRent());
        entity.setContractStatus(request.getContractStatus());
        entity.setAddress(request.getAddress());
        entity.setFullAddress(request.getFullAddress());
        entity.setWardId(request.getWardId());
        entity.setDistrictId(request.getDistrictId());
        entity.setProvinceId(request.getProvinceId());

        studentRepository.save(entity);

        return new StudentDto(entity);
    }

    @Override
    public Student updateStudent(Integer id, UpdateStudentRequest studentDetails) {

        return studentRepository.findById(id).map(student -> {
            // Cập nhật thông tin từ UpdateStudentRequest vào đối tượng student
            if (studentDetails.getFullName() != null) {
                student.setFullName(studentDetails.getFullName());
            }
            if (studentDetails.getEmail() != null) {
                student.setEmail(studentDetails.getEmail());
            }
            if (studentDetails.getPhoneNumber() != null) {
                student.setPhoneNumber(studentDetails.getPhoneNumber());
            }
            if (studentDetails.getCode() != null) {
                student.setCode(studentDetails.getCode());
            }
            if (studentDetails.getPassword() != null) {
                student.setPassword(studentDetails.getPassword());
            }
            if (studentDetails.getGender() != null) {
                student.setGender(studentDetails.getGender());
            }
            if (studentDetails.getDateOfBirth() != null) {
                student.setDateOfBirth(studentDetails.getDateOfBirth());
            }
            if (studentDetails.getFaculty() != null) {
                student.setFaculty(studentDetails.getFaculty());
            }
            if (studentDetails.getDormitoryId() != null) {
                student.setDormitoryId(studentDetails.getDormitoryId());
            }
            if (studentDetails.getRoom() != null) {
                student.setRoom(studentDetails.getRoom());
            }
            if (studentDetails.getClassId() != null) {
                student.setClassId(studentDetails.getClassId());
            }
            if (studentDetails.getMajorId() != null) {
                student.setMajorId(studentDetails.getMajorId());
            }
            if (studentDetails.getEducationLevel() != null) {
                student.setEducationLevel(studentDetails.getEducationLevel());
            }
            if (studentDetails.getResidenceStatus() != null) {
                student.setResidenceStatus(studentDetails.getResidenceStatus());
            }
            if (studentDetails.getAcademicYear() != null) {
                student.setAcademicYear(studentDetails.getAcademicYear());
            }
            if (studentDetails.getBirthplace() != null) {
                student.setBirthplace(studentDetails.getBirthplace());
            }
            if (studentDetails.getStatus() != null) {
                student.setStatus(studentDetails.getStatus());
            }
            if (studentDetails.getIsAdmin() != null) {
                student.setIsAdmin(studentDetails.getIsAdmin());
            }
            if (studentDetails.getAvatar() != null) {
                student.setAvatar(studentDetails.getAvatar());
            }
            if (studentDetails.getMonthlyRent() != null) {
                student.setMonthlyRent(studentDetails.getMonthlyRent());
            }
            if (studentDetails.getContractStatus() != null) {
                student.setContractStatus(studentDetails.getContractStatus());
            }
            if (studentDetails.getAddress() != null) {
                student.setAddress(studentDetails.getAddress());
            }
            if (studentDetails.getFullAddress() != null) {
                student.setFullAddress(studentDetails.getFullAddress());
            }
            if (studentDetails.getWardId() != null) {
                student.setWardId(studentDetails.getWardId());
            }
            if (studentDetails.getDistrictId() != null) {
                student.setDistrictId(studentDetails.getDistrictId());
            }
            if (studentDetails.getProvinceId() != null) {
                student.setProvinceId(studentDetails.getProvinceId());
            }

            // Lưu đối tượng sinh viên đã được cập nhật vào cơ sở dữ liệu
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public String deleteStudent(Integer id) {
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.deleteById(id);
        return "Student marked as deleted successfully";

    }

    @Override
    public String getMajorByStudentId(Integer id) {
        return studentRepository.findById(id).map(student -> student.getMajor().getName())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
