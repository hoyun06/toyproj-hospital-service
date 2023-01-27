package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    protected Patient() { }
}
