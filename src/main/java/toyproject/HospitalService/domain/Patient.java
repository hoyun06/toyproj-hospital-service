package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    private int age;
    private String name;
    private String gender;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public void changePatient(int age, String name, String gender) {
        this.setAge(age);
        this.setName(name);
        this.setGender(gender);
    }

    public void changePatientIdUpdatePurpose(Long id) {
        this.setId(id);
    }
    protected Patient() { }
}
