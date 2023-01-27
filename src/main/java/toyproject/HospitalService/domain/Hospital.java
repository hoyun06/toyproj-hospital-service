package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String name;

    @Embedded
    private Address address = new Address();

    @OneToMany(mappedBy = "hospital")
    private List<Department> departments = new ArrayList<>();

    protected Hospital() {}

    public Hospital(String name, String city, String street, String zipcode) {
        this.name = name;
        address.setCity(city);
        address.setStreet(street);
        address.setZipcode(zipcode);
    }
}
