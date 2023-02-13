package toyproject.HospitalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.HospitalService.domain.AccountUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AccountUser, Long> {

    Optional<AccountUser> findByUsername(String username);
    Optional<AccountUser> findByEmail(String email);
}
