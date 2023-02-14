package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.HospitalService.domain.Department;
import toyproject.HospitalService.domain.Doctor;
import toyproject.HospitalService.domain.Hospital;
import toyproject.HospitalService.repository.DepartmentRepository;
import toyproject.HospitalService.repository.DoctorRepository;
import toyproject.HospitalService.repository.HospitalRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;


    public Long addDoctor(Long hospitalId, Long departmentId, String name, int workYear) {
        Hospital findHospital = hospitalRepository.findOne(hospitalId);
        Department findDepartment = departmentRepository.findOne(departmentId);

        Doctor doctor = Doctor.createDoctor(findDepartment, findHospital, name, workYear);

        doctorRepository.save(doctor);
        return doctor.getId();
    }

    public Long updateDoctor(Doctor doctor, Long doctorId) {
        Doctor findDoctor = doctorRepository.findOne(doctorId);
        findDoctor.changeDoctor(doctor.getName(), doctor.getWorkYear());
        return doctorId;
    }

    public Long deleteDoctor(Long doctorId) {
        doctorRepository.remove(doctorId);
        return doctorId;
    }

    public Doctor findOneDoctor(Long id) {
        return doctorRepository.findOne(id);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findDoctorsByDepartmentId(Long id) {
        return doctorRepository.findByDepartmentId(id);
    }
}