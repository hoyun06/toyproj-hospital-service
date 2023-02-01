package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toyproject.HospitalService.domain.AccountUser;
import toyproject.HospitalService.domain.UserRole;
import toyproject.HospitalService.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountUser> findUser = userRepository.findByUsername(username);

        if(findUser.isEmpty())
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        AccountUser validUser = findUser.get();

        if("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new User(validUser.getUsername(), validUser.getPassword(), authorities);
    }
}
