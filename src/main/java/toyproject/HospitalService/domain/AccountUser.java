package toyproject.HospitalService.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class AccountUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    public void updateUsername(String username) {
        this.setUsername(username);
    }

    public void updatePassword(String password) {
        this.setPassword(password);
    }

    public void updateEmail(String email) {
        this.setEmail(email);
    }
}
