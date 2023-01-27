package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.HospitalService.domain.*;
import toyproject.HospitalService.repository.*;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public Long addAppointment(Long hospitalId, String departmentName, Long doctorId, Long patientId) {
        Hospital findHospital = hospitalRepository.findOne(hospitalId);
        Department findDepartment = departmentRepository.findOne(departmentName);
        Doctor findDoctor = doctorRepository.findOne(doctorId);
        Patient findPatient = patientRepository.findOne(patientId);

        Appointment appointment = Appointment.createAppointment(findHospital, findDepartment, findDoctor, findPatient);

        appointmentRepository.save(appointment);

        return appointment.getId();
    }

    public Appointment findOneAppointment(Long id) {
        return appointmentRepository.findOne(id);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAppointmentsByPatient(Long id) {
        return appointmentRepository.findByPatient(id);
    }

    public List<Appointment> findAppointmentsByDoctor(Long id) {
        return appointmentRepository.findByDoctor(id);
    }

    public void cancelAppointment(Long id) {
        Appointment findAppointment = appointmentRepository.findOne(id);
        findAppointment.cancel();
    }

    public void finishAppointment(Long id) {
        Appointment findAppointment = appointmentRepository.findOne(id);
        findAppointment.completeAppointment();
    }
}
