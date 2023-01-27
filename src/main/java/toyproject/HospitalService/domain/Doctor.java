package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments = new ArrayList<>();

    private String name;
    private int workYear;

    protected Doctor() {}

    public void addDepartment(Department department) {
        this.setDepartment(department);
        department.getDoctors().add(this);
    }
    public static Doctor createDoctor(Department department, Hospital hospital, String name, int workYear) {
        Doctor doctor = new Doctor();
        doctor.addDepartment(department);
        doctor.setHospital(hospital);
        doctor.setName(name);
        doctor.setWorkYear(workYear);

        return doctor;
    }
}