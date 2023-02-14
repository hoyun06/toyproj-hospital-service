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

        Long departmentId = departmentService.addDepartment(hospitalId, departmentForm.getName(), departmentForm.getTel());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentId", departmentId);
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
    public String departmentList(Model model, @RequestParam("department_id") Long departmentId) {
        List<Doctor> doctors = doctorService.findDoctorsByDepartmentId(departmentId);
        model.addAttribute("doctors", doctors);
        return "doctor/doctorList";
    }

    @GetMapping("/department/modify")
    public String departmentModifyForm(@RequestParam("department_id") Long departmentId, Model model) {
        Department findDepartment = departmentService.findOneDepartment(departmentId);

        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setName(findDepartment.getName());
        departmentForm.setTel(findDepartment.getTel());

        model.addAttribute("departmentForm",departmentForm);
        model.addAttribute("departmentId", departmentId);
        return "department/departmentModifyForm";
    }

    @PostMapping("/department/modify")
    public String updateDepartment(DepartmentForm departmentForm, @RequestParam("department_id") Long departmentId) {
        Department department = new Department();
        department.changeDepartment(departmentForm.getName(), departmentForm.getTel());
        department.changeDepartmentIdUpdatePurpose(departmentId);
        departmentService.updateDepartment(department);
        return "redirect:/hospital/list";
    }

    @GetMapping("/department/delete")
    public String departmentDelete(@RequestParam("department_id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/hospital/list";
    }
}
