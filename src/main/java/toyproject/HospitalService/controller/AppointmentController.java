package toyproject.HospitalService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.HospitalService.domain.*;
import toyproject.HospitalService.dto.AppointmentForm;
import toyproject.HospitalService.service.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppointmentController {

    private final HospitalService hospitalService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @GetMapping("/appointment/hospital")
    public String appointmentHospitalForm(Model model) {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        return "appointment/appointmentHospitalForm";
    }

    @PostMapping("/appointment/hospital")
    public String completeHospital(@RequestParam("hospital_id") Long hospitalId, Model model) {
        List<Department> departments = departmentService.findDepartmentsByHospitalId(hospitalId);

        model.addAttribute("departments", departments);
        model.addAttribute("hospitalId", hospitalId);
        return "appointment/appointmentDepartmentForm";
    }

    @PostMapping("/appointment/department")
    public String completeDepartment(@RequestParam("hospital_id") Long hospitalId
            ,@RequestParam("department_name") String name, Model model) {

        List<Doctor> doctors = doctorService.findDoctorsByDepartmentName(name);

        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentName", name);
        model.addAttribute("doctors", doctors);
        return "appointment/appointmentDoctorForm";
    }

    @PostMapping("/appointment/doctor")
    public String completeDoctor(@RequestParam("hospital_id") Long hospitalId
            ,@RequestParam("department_name") String name
            ,@RequestParam("doctor_id") Long doctorId, Model model) {

        List<Patient> patients = patientService.findAllPatients();

        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentName", name);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("patients", patients);
        return "appointment/appointmentPatientForm";
    }

    @PostMapping("/appointment/patient")
    public String completePatient(@RequestParam("hospital_id") Long hospitalId
            ,@RequestParam("department_name") String name
            ,@RequestParam("doctor_id") Long doctorId
            ,@RequestParam("patient_id") Long patientId, Model model) {
        Long appointmentId = appointmentService.addAppointment(hospitalId, name, doctorId, patientId);

        List<Appointment> appointments = appointmentService.findAppointmentsByPatient(patientId);

        model.addAttribute("appointments", appointments);
        return "appointment/appointmentListByPatient";
    }

    @GetMapping("/appointment/list")
    public String appointmentList(Model model) {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointment/appointmentList";
    }
}
