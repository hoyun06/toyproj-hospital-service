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
import toyproject.HospitalService.domain.Hospital;
import toyproject.HospitalService.dto.DepartmentForm;
import toyproject.HospitalService.dto.HospitalForm;
import toyproject.HospitalService.service.DepartmentService;
import toyproject.HospitalService.service.HospitalService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    @GetMapping("/register/hospital")
    public String hospitalForm(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());
        return "hospital/hospitalForm";
    }

    @PostMapping("/register/hospital")
    public String newHospital(@Valid HospitalForm hospitalForm, BindingResult result, Model model) {
        if(result.hasErrors())
            return "hospital/hospitalForm";

        Hospital hospital = new Hospital(hospitalForm.getName(),hospitalForm.getCity(), hospitalForm.getStreet(), hospitalForm.getZipcode());
        Long id = hospitalService.addHospital(hospital);
        model.addAttribute("departmentForm", new DepartmentForm());
        model.addAttribute("hospitalId", id);
        return "department/departmentForm";
    }

    @GetMapping("/hospital/list")
    public String hospitalList(Model model) {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        return "hospital/hospitalList";
    }

    @GetMapping("/hospital/detail")
    public String hospitalList(Model model, @RequestParam("hospital_id") Long hospitalId) {
        List<Department> departments = departmentService.findDepartmentsByHospitalId(hospitalId);
        model.addAttribute("departments", departments);
        return "department/departmentList";
    }
}