package toyproject.HospitalService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.HospitalService.domain.Doctor;
import toyproject.HospitalService.dto.DoctorForm;
import toyproject.HospitalService.service.DoctorService;

@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register/doctor")
    public String doctorForm(@Valid DoctorForm doctorForm, @RequestParam("hospital_id") Long hospitalId
            , @RequestParam("department_id") Long departmentId, BindingResult result) {
        if(result.hasErrors())
            return "doctor/doctorForm";

        doctorService.addDoctor(hospitalId, departmentId, doctorForm.getName(), doctorForm.getWorkYear());

        return "home";
    }

    @GetMapping("/register/doctor")
    public String doctorFormNoDepartment(@RequestParam("hospital_id") Long hospitalId
            , @RequestParam("department_id") Long departmentId, Model model) {

        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("doctorForm",new DoctorForm());
        return "doctor/doctorForm";
    }

    @GetMapping("/doctor/modify")
    public String doctorModifyForm(@RequestParam("doctor_id") Long doctorId, Model model) {
        Doctor findDoctor = doctorService.findOneDoctor(doctorId);

        DoctorForm doctorForm = new DoctorForm();
        doctorForm.setName(findDoctor.getName());
        doctorForm.setWorkYear(findDoctor.getWorkYear());

        model.addAttribute("doctorForm", doctorForm);
        model.addAttribute("doctorId", doctorId);
        return "doctor/doctorModifyForm";
    }

    @PostMapping("/doctor/modify")
    public String updateDoctor(DoctorForm doctorForm, @RequestParam("doctor_id") Long doctorId) {

        Doctor doctor = new Doctor();
        doctor.changeDoctor(doctorForm.getName(), doctorForm.getWorkYear());
        doctor.changeDoctorIdUpdatePurpose(doctorId);

        doctorService.updateDoctor(doctor, doctorId);
        return "redirect:/hospital/list";
    }

    @GetMapping("/doctor/delete")
    public String doctorDelete(@RequestParam("doctor_id") Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/hospital/list";
    }
}
