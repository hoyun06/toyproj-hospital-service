package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import toyproject.HospitalService.domain.AccountUser;
import toyproject.HospitalService.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountUser createUser(String username, String password, String email) {
        AccountUser user = new AccountUser();
        user.updateUsername(username);
        user.updateEmail(email);
        user.updatePassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public Optional<AccountUser> findUser(String username) {
        return userRepository.findByUsername(username);
    }
}
