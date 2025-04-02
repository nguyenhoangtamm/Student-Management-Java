package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Major.MajorDto;
import com.tam.StudentManagement.Model.Major;
import com.tam.StudentManagement.Repository.MajorRepository;
import com.tam.StudentManagement.Request.Major.CreateMajorRequest;
import com.tam.StudentManagement.Request.Major.UpdateMajorRequest;
import com.tam.StudentManagement.Service.Interface.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService implements IMajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    @Override
    public Optional<Major> getMajorById(Integer id) {
        return majorRepository.findById(id);
    }

    @Override
    public MajorDto createMajor(CreateMajorRequest request) {
        Major entity = new Major();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setTotalStudent(0);

        majorRepository.save(entity);

        return new MajorDto(entity);
    }

    @Override
    public Major updateMajor(Integer id, UpdateMajorRequest majorDetails) {
        return majorRepository.findById(id).map(major -> {
            if (majorDetails.getCode() != null) {
                major.setCode(majorDetails.getCode());
            }
            if (majorDetails.getName() != null) {
                major.setName(majorDetails.getName());
            }
            return majorRepository.save(major);
        }).orElse(null);
    }

    @Override
    public String deleteMajor(Integer id) {
        return majorRepository.findById(id).map(major -> {
            major.setIsDelete(true);
            majorRepository.save(major);
            return "Major deleted successfully";
        }).orElse("Major not found");
    }
}