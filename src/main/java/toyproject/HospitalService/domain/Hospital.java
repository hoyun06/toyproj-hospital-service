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

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    private List<Department> departments = new ArrayList<>();

    public void changeHospital(String name, Address address) {
        this.setName(name);
        this.setAddress(address);
    }

    public void changeHospitalIdUpdatePurpose(Long id) {
        this.setId(id);
    }

    protected Hospital() {}

    public Hospital(String name, String city, String street, String zipcode) {
        this.name = name;
        address.setCity(city);
        address.setStreet(street);
        address.setZipcode(zipcode);
    }
}
