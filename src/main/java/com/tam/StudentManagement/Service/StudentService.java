package com.tam.StudentManagement.Service;

import com.tam.StudentManagement.Dto.Student.StudentDto;
import com.tam.StudentManagement.Dto.Student.StudentHeaderInfoDto;
import com.tam.StudentManagement.Dto.Student.StudentNotificationDto;
import com.tam.StudentManagement.Dto.Student.StudentProfileDto;
import com.tam.StudentManagement.Dto.Student.StudentServiceDto;
import com.tam.StudentManagement.Dto.Student.StudentStatisticsDto;
import com.tam.StudentManagement.Dto.Student.StudentStatusDto;
import com.tam.StudentManagement.Enum.Student.GenderEnum;
import com.tam.StudentManagement.Enum.Student.StatusEnum;
import com.tam.StudentManagement.Enum.Student.EducationLevelEnum;
import com.tam.StudentManagement.Enum.Student.ResidenceStatusEnum;
import com.tam.StudentManagement.Enum.Student.EducationTypeEnum;
import com.tam.StudentManagement.Enum.Student.ContractStatusEnum;
import com.tam.StudentManagement.Dto.Notification.NotificationDto;
import com.tam.StudentManagement.Exception.DuplicateException;
import com.tam.StudentManagement.Dto.Common.PaginationDto;
import com.tam.StudentManagement.Dto.Common.PaginationInfo;
import com.tam.StudentManagement.Dto.Student.CreateStudentDto;
import com.tam.StudentManagement.Dto.Student.OffCampusDto;
import com.tam.StudentManagement.Dto.Student.StudentDashboardDto;
import com.tam.StudentManagement.Model.*;
import com.tam.StudentManagement.Repository.*;
import com.tam.StudentManagement.Request.Student.CreateStudentRequest;
import com.tam.StudentManagement.Request.Student.UpdateStudentRequest;
import com.tam.StudentManagement.Security.StudentDetails;
import com.tam.StudentManagement.Service.Interface.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
// Removed incorrect CouchbaseProperties.Authentication import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public CreateStudentDto createStudent(CreateStudentRequest request) {
        Student exitEntity = studentRepository.findByCode(request.getCode());
        if (exitEntity != null) {
            throw new DuplicateException("Student code already exists");
        }
        exitEntity = studentRepository.findByEmail(request.getEmail());
        if (exitEntity != null) {
            throw new DuplicateException("Email already exists");
        }
        exitEntity = studentRepository.findByPhoneNumber(request.getPhoneNumber());
        if (exitEntity != null) {
            throw new DuplicateException("Phone number already exists");
        }

        Student entity = new Student();
        entity.setCode(request.getCode());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setFullName(request.getFullName());
        entity.setGender(request.getGender());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setFaculty(request.getFaculty());

        // Set dormitory relationship if dormitoryId is provided
        if (request.getDormitoryId() != null) {
            Optional<Dormitory> dormitory = dormitoryRepository.findById(request.getDormitoryId());
            dormitory.ifPresent(entity::setDormitory);
        }

        entity.setRoom(request.getRoom());

        // Set major relationship
        if (request.getMajorId() != null) {
            Optional<Major> major = majorRepository.findById(request.getMajorId());
            major.ifPresent(entity::setMajor);
        }

        // Set student class relationship
        if (request.getClassId() != null) {
            Optional<StudentClass> studentClass = studentClassRepository.findById(request.getClassId());
            studentClass.ifPresent(entity::setStudentClass);
        }

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

        // Set ward relationship
        if (request.getWardId() != null) {
            Optional<Ward> ward = wardRepository.findById(request.getWardId());
            ward.ifPresent(entity::setWard);
        }

        // Set district relationship
        if (request.getDistrictId() != null) {
            Optional<District> district = districtRepository.findById(request.getDistrictId());
            district.ifPresent(entity::setDistrict);
        }

        // Set province relationship
        if (request.getProvinceId() != null) {
            Optional<Province> province = provinceRepository.findById(request.getProvinceId());
            province.ifPresent(entity::setProvince);
        }

        studentRepository.save(entity);

        return new CreateStudentDto(entity);
    }

    @Override
    public Student updateStudent(Integer id, UpdateStudentRequest studentDetails) {
        Student exitEntity = studentRepository.findByCode(studentDetails.getCode());
        if (exitEntity != null) {
            throw new DuplicateException("Student code already exists");
        }
        exitEntity = studentRepository.findByEmail(studentDetails.getEmail());
        if (exitEntity != null) {
            throw new DuplicateException("Email already exists");
        }
        exitEntity = studentRepository.findByPhoneNumber(studentDetails.getPhoneNumber());
        if (exitEntity != null) {
            throw new DuplicateException("Phone number already exists");
        }

        return studentRepository.findById(id).map(student -> {
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
                student.setPassword(passwordEncoder.encode(studentDetails.getPassword()));
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
                Optional<Dormitory> dormitory = dormitoryRepository.findById(studentDetails.getDormitoryId());
                dormitory.ifPresent(student::setDormitory);
            }
            if (studentDetails.getRoom() != null) {
                student.setRoom(studentDetails.getRoom());
            }
            if (studentDetails.getMajorId() != null) {
                Optional<Major> major = majorRepository.findById(studentDetails.getMajorId());
                major.ifPresent(student::setMajor);
            }
            if (studentDetails.getClassId() != null) {
                Optional<StudentClass> studentClass = studentClassRepository.findById(studentDetails.getClassId());
                studentClass.ifPresent(student::setStudentClass);
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
                Optional<Ward> ward = wardRepository.findById(studentDetails.getWardId());
                ward.ifPresent(student::setWard);
            }
            if (studentDetails.getDistrictId() != null) {
                Optional<District> district = districtRepository.findById(studentDetails.getDistrictId());
                district.ifPresent(student::setDistrict);
            }
            if (studentDetails.getProvinceId() != null) {
                Optional<Province> province = provinceRepository.findById(studentDetails.getProvinceId());
                province.ifPresent(student::setProvince);
            }
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
    public StudentDashboardDto getDashboard() {
        Student student = new Student();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof StudentDetails) {
            StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
            student = studentDetails.getStudent();
        }
        StudentDashboardDto.OffCampusInfo offCampusInfo = new StudentDashboardDto.OffCampusInfo(
                student.getDormitory() != null ? student.getDormitory().getName() : "KTX Đại học Đồng Tháp",
                student.getContractStatus() != null && student.getContractStatus() == 1 ? "Đã ký hợp đồng"
                        : "Chưa ký hợp đồng",
                "2025-03-09T15:00:50.000000Z",
                student.getRoom(),
                student.getFullAddress());
        GenderEnum gender = GenderEnum.fromValue(student.getGender());

        return new StudentDashboardDto(
                student.getCode(),
                student.getFullName(),
                gender.getLabel(),
                student.getDateOfBirth().toString(),
                student.getBirthplace(),
                student.getFaculty(),
                student.getEmail(),
                student.getAvatar(),
                1, // TODO: Implement actual unread notifications count
                offCampusInfo,
                new Object[] {} // TODO: Implement actual notifications
        );
    }

    @Override
    public PaginationDto<StudentDto> getStudentsByPagination(int pageNumber, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // Convert to 0-based page number
        Page<Student> studentPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            studentPage = studentRepository
                    .findByFullNameContainingOrCodeContainingOrEmailContainingOrPhoneNumberContaining(
                            keyword, keyword, keyword, keyword, pageable);
        } else {
            studentPage = studentRepository.findAll(pageable);
        }

        List<StudentDto> studentDtos = studentPage.getContent().stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());

        PaginationInfo paginationInfo = new PaginationInfo(
                pageNumber,
                pageSize,
                studentPage.getTotalElements(),
                studentPage.getTotalPages());

        return new PaginationDto<StudentDto>(studentDtos, paginationInfo);
    }

    @Override
    public StudentHeaderInfoDto getHeaderInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof StudentDetails) {
            StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
            Integer studentId = studentDetails.getStudent().getId();

            // Lấy lại từ DB để đảm bảo fetch được notifications
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            List<StudentNotificationDto> notificationDtos = new ArrayList<>();
            for (StudentNotification studentNotification : student.getStudentNotifications()) {
                Notification notification = studentNotification.getNotification();
                notificationDtos.add(new StudentNotificationDto(
                        notification.getId(),
                        notification.getTitle(),

                        notification.getSlug()));
            }

            return new StudentHeaderInfoDto(student.getFullName(), student.getAvatar(), notificationDtos);
        }

        // Trường hợp không đăng nhập hoặc lỗi xác thực
        return new StudentHeaderInfoDto(null, null, new ArrayList<>());
    }

    public StudentProfileDto getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof StudentDetails) {
            StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
            Student student = studentRepository.findById(studentDetails.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            List<StudentServiceDto> studentServiceDto = student.getDormitory() != null
                    ? student.getDormitory().getDormitoryServices().stream()
                            .map(studentService -> new StudentServiceDto(
                                    studentService.getService().getName(),
                                    studentService.getFee(),
                                    studentService.getService().getUnit()))
                            .collect(Collectors.toList())
                    : new ArrayList<>();

            ContractStatusEnum contractStatus = student.getContractStatus() != null
                    ? ContractStatusEnum.fromValue(student.getContractStatus())
                    : ContractStatusEnum.CHUA_KY;

            OffCampusDto offCampusDto = new OffCampusDto(
                    student.getDormitory() != null ? student.getDormitory().getName() : null,
                    student.getDormitory() != null ? student.getDormitory().getAddress() : null,
                    student.getDormitory() != null ? student.getDormitory().getOwnerName() : null,
                    student.getDormitory() != null ? student.getDormitory().getPhoneNumber() : null,
                    contractStatus.getLabel(),
                    student.getRoom(),
                    student.getMonthlyRent() != null ? student.getMonthlyRent().toString() : null,
                    studentServiceDto);
            GenderEnum gender = GenderEnum.fromValue(student.getGender());
            StatusEnum status = StatusEnum.fromValue(student.getStatus());
            EducationLevelEnum educationLevel = student.getEducationLevel() != null
                    ? EducationLevelEnum.fromValue(student.getEducationLevel())
                    : EducationLevelEnum.DAI_HOC;
            ResidenceStatusEnum residenceStatus = ResidenceStatusEnum.fromValue(student.getResidenceStatus());
            EducationTypeEnum educationType = EducationTypeEnum.fromValue(student.getEducationType());
            return new StudentProfileDto(
                    student.getCode(),
                    student.getFullName(),
                    gender.getLabel().toString(),
                    student.getAvatar(),
                    status.getLabel(),
                    student.getStudentClass() != null ? student.getStudentClass().getName() : "",
                    educationLevel.getLabel(),
                    student.getFaculty(),
                    student.getMajor() != null ? student.getMajor().getName() : "",
                    educationType.getLabel(),
                    student.getAcademicYear(),
                    student.getPhoneNumber(),
                    student.getFullAddress(),
                    student.getDateOfBirth().toString(),
                    student.getBirthplace(),
                    student.getEmail(),
                    residenceStatus.getLabel(),
                    offCampusDto

            );
        }
        throw new RuntimeException("Authentication failed or user not logged in");
    }
    @Override
    public  StudentStatisticsDto getStatistic(){
        int confirmedStudents = studentRepository.countByResidenceStatus(1);
        int unconfirmedStudents = studentRepository.countByResidenceStatus(0);
        int atHomeStudents = studentRepository.countByResidenceStatus(2);
        int otherStudents = studentRepository.countByResidenceStatus(3);

        StudentStatisticsDto studentStatisticsDto = new StudentStatisticsDto(
             confirmedStudents, unconfirmedStudents, atHomeStudents, otherStudents
        );
        return studentStatisticsDto;
        
       
    }
    
    @Override
    public   StudentStatusDto getStatus(){
        int totalStudents = (int) studentRepository.count();
        int totalDormitories = (int) dormitoryRepository.count();
        int confirmedStudents = studentRepository.countByResidenceStatus(1);
        int unconfirmedStudents = studentRepository.countByResidenceStatus(0);
        StudentStatusDto studentStatusDto = new StudentStatusDto(
            totalStudents, totalDormitories, confirmedStudents,
             unconfirmedStudents);
        return studentStatusDto;
       
    }
}
