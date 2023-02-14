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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    private String name;

    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<Doctor> doctors = new ArrayList<>();


    public void addHospital(Hospital hospital) {
        this.setHospital(hospital);
        hospital.getDepartments().add(this);
    }

    public void changeDepartment(String name, String tel) {
        this.setName(name);
        this.setTel(tel);
    }

    public void changeDepartmentIdUpdatePurpose(Long id) {
        this.setId(id);
    }
    public Department() {}
    public static Department createDepartment(Hospital hospital, String name, String tel) {
        Department department = new Department();
        department.addHospital(hospital);
        department.setName(name);
        department.setTel(tel);

        return department;
    }
}
