package com.tam.StudentManagement.Controller;

import com.tam.StudentManagement.Dto.Major.MajorDto;
import com.tam.StudentManagement.Model.Major;
import com.tam.StudentManagement.Request.Major.CreateMajorRequest;
import com.tam.StudentManagement.Request.Major.UpdateMajorRequest;
import com.tam.StudentManagement.Service.Interface.IMajorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
public class MajorController {

    @Autowired
    private IMajorService majorService;

    // Lấy danh sách ngành học
    @GetMapping
    public ResponseEntity<List<Major>> getAllMajors() {
        return ResponseEntity.ok(majorService.getAllMajors());
    }

    // Lấy thông tin ngành học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Major> getMajorById(@PathVariable Integer id) {
        return majorService.getMajorById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm mới ngành học
    @PostMapping
    public ResponseEntity<MajorDto> createMajor(@Valid @RequestBody CreateMajorRequest major) {
        return ResponseEntity.ok(majorService.createMajor(major));
    }

    // Cập nhật thông tin ngành học
    @PutMapping("/{id}")
    public ResponseEntity<Major> updateMajor(@PathVariable Integer id,
            @Valid @RequestBody UpdateMajorRequest majorDetails) {
        return ResponseEntity.ok(majorService.updateMajor(id, majorDetails));
    }

    // Xóa ngành học (đánh dấu isDelete thay vì xóa thật sự)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMajor(@PathVariable Integer id) {
        return ResponseEntity.ok(majorService.deleteMajor(id));
    }
}
