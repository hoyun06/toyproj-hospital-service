package toyproject.HospitalService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.HospitalService.domain.Patient;
import toyproject.HospitalService.dto.PatientForm;
import toyproject.HospitalService.service.PatientService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patient/new")
    public String patientForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        return "patient/patientForm";
    }

    @PostMapping("/patient/new")
    public String newPatient(@Valid PatientForm patientForm, BindingResult result) {
        if(result.hasErrors())
            return "patient/patientForm";
        patientService.join(new Patient(patientForm.getAge(), patientForm.getName(), patientForm.getGender()));
        return "home";
    }

    @GetMapping("/patient/list")
    public String patientList(Model model) {
        List<Patient> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return "/patient/patientList";
    }
}
