package toyproject.HospitalService.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "사용자 ID는 필수입니다.")
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty(message = "사용자 비밀번호는 필수입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인란은 필수입니다.")
    private String password2;

    @NotEmpty(message = "사용자 이메일은 필수입니다.")
    @Email
    private String email;
}
