package toyproject.HospitalService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.HospitalService.domain.Department;
import toyproject.HospitalService.domain.Doctor;
import toyproject.HospitalService.dto.DepartmentForm;
import toyproject.HospitalService.dto.DoctorForm;
import toyproject.HospitalService.service.DepartmentService;
import toyproject.HospitalService.service.DoctorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    @PostMapping("/register/department")
    public String departmentForm(@Valid DepartmentForm departmentForm
            , @RequestParam("hospital_id") Long hospitalId, BindingResult result, Model model) {
        if(result.hasErrors())
            return "department/departmentForm";

        String departmentName = departmentService.addDepartment(hospitalId, departmentForm.getName(), departmentForm.getTel());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentName", departmentName);
        model.addAttribute("doctorForm", new DoctorForm());
        return "doctor/doctorForm";
    }

    @GetMapping("/register/department")
    public String departmentFormNoHospital(@RequestParam("hospital_id") Long hospitalId, Model model) {
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentForm", new DepartmentForm());
        return "department/departmentForm";
    }
    @GetMapping("/register/department_doctor")
    public String departmentOption(@RequestParam("hospital_id") Long hospitalId, Model model) {
        List<Department> departments = departmentService.findDepartmentsByHospitalId(hospitalId);

        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departments", departments);
        return "department/departmentOption";
    }

    @GetMapping("/department/detail")
    public String departmentList(Model model, @RequestParam("department_name") String name) {
        List<Doctor> doctors = doctorService.findDoctorsByDepartmentName(name);
        model.addAttribute("doctors", doctors);
        return "doctor/doctorList";
    }
}
