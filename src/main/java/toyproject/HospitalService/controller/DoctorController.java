package toyproject.HospitalService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.HospitalService.dto.DoctorForm;
import toyproject.HospitalService.service.DoctorService;

@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register/doctor")
    public String doctorForm(@Valid DoctorForm doctorForm, @RequestParam("hospital_id") Long hospitalId
            , @RequestParam("department_name") String departmentName, BindingResult result) {
        if(result.hasErrors())
            return "doctor/doctorForm";

        doctorService.addDoctor(hospitalId, departmentName, doctorForm.getName(), doctorForm.getWorkYear());

        return "home";
    }
}
