package toyproject.HospitalService.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientForm {

    @NotNull(message = "나이는 필수입니다")
    private int age;
    @NotEmpty(message = "이름은 필수입니다")
    private String name;
    @NotEmpty(message = "성별은 필수입니다")
    private String gender;
}
