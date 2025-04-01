package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Student.StudentDto;
import com.tam.StudentManagement.Model.Student;
import com.tam.StudentManagement.Request.Student.CreateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateStudentRequest;
import com.tam.StudentManagement.Service.Interface.IStudentService;
import com.tam.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    // Lấy danh sách sinh viên
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Lấy thông tin sinh viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm mới sinh viên
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody CreateStudentRequest student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }


//     Cập nhật thông tin sinh viên
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent( @PathVariable Integer id,@Valid @RequestBody UpdateStudentRequest studentDetails) {
        return ResponseEntity.ok(studentService.updateStudent(id,studentDetails));

    }

    // Xóa sinh viên (đánh dấu isDelete thay vì xóa thật sự)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));


    }

}
