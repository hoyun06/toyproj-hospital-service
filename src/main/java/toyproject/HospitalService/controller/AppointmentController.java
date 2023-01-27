package toyproject.HospitalService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.HospitalService.domain.Department;
import toyproject.HospitalService.domain.Doctor;
import toyproject.HospitalService.domain.Hospital;
import toyproject.HospitalService.dto.AppointmentForm;
import toyproject.HospitalService.service.AppointmentService;
import toyproject.HospitalService.service.DepartmentService;
import toyproject.HospitalService.service.DoctorService;
import toyproject.HospitalService.service.HospitalService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppointmentController {

    private final HospitalService hospitalService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String appointmentForm(Model model) {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        List<Department> departments = departmentService.findAllDepartments();
        List<Doctor> doctors = doctorService.findAllDoctors();

        model.addAttribute("appointmentForm", new AppointmentForm());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);

        return "appointment/appointmentForm";
    }

}
