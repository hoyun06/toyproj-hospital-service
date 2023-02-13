package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class Department {

    @Id
    @Column(name = "department_name")
    private String name;

    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<Doctor> doctors = new ArrayList<>();

    protected Department() {}

    public void addHospital(Hospital hospital) {
        this.setHospital(hospital);
        hospital.getDepartments().add(this);
    }

    public static Department createDepartment(Hospital hospital, String name, String tel) {
        Department department = new Department();
        department.addHospital(hospital);
        department.setName(name);
        department.setTel(tel);

        return department;
    }
}
