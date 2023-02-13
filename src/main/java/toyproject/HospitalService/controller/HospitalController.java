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

    @GetMapping("/hospital/option/department")
    public String hospitalOptionForDepartment(Model model) {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        return "hospital/hospitalOptionForDepartment";
    }

    @GetMapping("/hospital/option/doctor")
    public String hospitalOptionForDoctor(Model model) {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        return "hospital/hospitalOptionForDoctor";
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
        model.addAttribute("hospitalId", hospitalId);
        return "department/departmentList";
    }

    @GetMapping("/hospital/modify")
    public String hospitalModifyForm(@RequestParam("hospital_id") Long hospitalId, Model model) {
        Hospital hospital = hospitalService.getOneHospital(hospitalId);
        HospitalForm hospitalForm = new HospitalForm();
        hospitalForm.setName(hospital.getName());
        hospitalForm.setCity(hospital.getAddress().getCity());
        hospitalForm.setStreet(hospital.getAddress().getStreet());
        hospitalForm.setZipcode(hospital.getAddress().getZipcode());

        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("hospitalForm", hospitalForm);
        return "hospital/hospitalModifyForm";
    }

    @PostMapping("/hospital/modify")
    public String updateHospital(HospitalForm hospitalForm, @RequestParam("hospital_id") Long hospitalId) {
        Hospital hospital = new Hospital(hospitalForm.getName(), hospitalForm.getCity(), hospitalForm.getStreet(), hospitalForm.getZipcode());
        hospital.changeHospitalIdUpdatePurpose(hospitalId);

        hospitalService.updateHospital(hospital);
        return "redirect:/hospital/list";
    }

    @GetMapping("/hospital/delete")
    public String hospitalDelete(@RequestParam("hospital_id") Long id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospital/list";
    }
}
