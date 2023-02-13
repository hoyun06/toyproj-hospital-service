package toyproject.HospitalService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.HospitalService.domain.AccountUser;
import toyproject.HospitalService.dto.UserForm;
import toyproject.HospitalService.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @GetMapping("/user/join")
    public String join(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/join";
    }

    @PostMapping("/user/join")
    public String joinCheck(@Valid UserForm userForm, BindingResult result) {
        if(result.hasErrors())
            return "user/join";

        Optional<AccountUser> findUser = userService.findUser(userForm.getUsername());
        if(findUser.isPresent()) {
            result.rejectValue("username", "existingUsername"
                    , "이미 존재하는 사용자 ID입니다.");
            return "user/join";
        }

        Optional<AccountUser> userByEmail = userService.findUserByEmail(userForm.getEmail());
        if(userByEmail.isPresent()) {
            result.rejectValue("email", "existingUserEmail", "이미 존재하는 이메일입니다.");
            return "user/join";
        }

        if(!userForm.getPassword1().equals(userForm.getPassword2())) {
            result.rejectValue("password2", "passwordIncorrect"
                    , "두 비밀번호가 일치하지 않습니다.");
            return "user/join";
        }

        userService.createUser(userForm.getUsername(), userForm.getPassword1(), userForm.getEmail());
        return "redirect:/";
    }


}
