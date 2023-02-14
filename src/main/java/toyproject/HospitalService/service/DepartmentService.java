package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.HospitalService.domain.Department;
import toyproject.HospitalService.domain.Hospital;
import toyproject.HospitalService.repository.DepartmentRepository;
import toyproject.HospitalService.repository.HospitalRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public Long addDepartment(Long hospitalId, String name, String tel) {
        Hospital findHospital = hospitalRepository.findOne(hospitalId);
        Department department = Department.createDepartment(findHospital, name, tel);
        departmentRepository.save(department);
        return department.getId();
    }

    public Long updateDepartment(Department department) {
        Department findDepartment = departmentRepository.findOne(department.getId());
        findDepartment.changeDepartment(department.getName(), department.getTel());
        return department.getId();
    }

    public Long deleteDepartment(Long departmentId) {
        departmentRepository.remove(departmentId);
        return departmentId;
    }
    public Department findOneDepartment(Long id) {
        return departmentRepository.findOne(id);
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> findDepartmentsByHospitalId(Long id) {
        return departmentRepository.findByHospitalId(id);
    }
}
