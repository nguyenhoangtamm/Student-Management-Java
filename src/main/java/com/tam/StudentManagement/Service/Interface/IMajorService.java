package com.tam.StudentManagement.Service.Interface;

import com.tam.StudentManagement.Dto.Major.MajorDto;
import com.tam.StudentManagement.Model.Major;
import com.tam.StudentManagement.Request.Major.CreateMajorRequest;
import com.tam.StudentManagement.Request.Major.UpdateMajorRequest;

import java.util.List;
import java.util.Optional;

public interface IMajorService {
    List<Major> getAllMajors();

    Optional<Major> getMajorById(Integer id);

    MajorDto createMajor(CreateMajorRequest major);

    Major updateMajor(Integer id, UpdateMajorRequest majorDetails);

    String deleteMajor(Integer id);
}