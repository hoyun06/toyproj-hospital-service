package toyproject.HospitalService.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DoctorForm {

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    private int workYear;
}
