package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_name")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    private LocalDate appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    private int price;

    public void addPatient(Patient patient) {
        this.setPatient(patient);
        patient.getAppointments().add(this);
    }
    public void addDoctor(Doctor doctor) {
        this.setDoctor(doctor);
        doctor.getAppointments().add(this);
    }

    protected Appointment () {}
    public static Appointment createAppointment(Hospital hospital, Department department, Doctor doctor, Patient patient, LocalDate date) {
        Appointment appointment = new Appointment();
        appointment.setHospital(hospital);
        appointment.setDepartment(department);
        appointment.addDoctor(doctor);
        appointment.addPatient(patient);
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
        appointment.setAppointmentTime(date);
        appointment.setPrice(10000);

        return appointment;
    }

    public void cancel() {
        this.setAppointmentStatus(AppointmentStatus.CANCELED);
//        this.getDoctor().getAppointments().remove(this);
//        this.getPatient().getAppointments().remove(this);
    }

    public void completeAppointment() {
        this.setAppointmentStatus(AppointmentStatus.COMPLETED);
//        this.getDoctor().getAppointments().remove(this);
//        this.getPatient().getAppointments().remove(this);
    }
}