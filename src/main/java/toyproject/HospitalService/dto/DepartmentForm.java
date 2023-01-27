package toyproject.HospitalService.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentForm {

    @NotEmpty(message = "진료과는 필수입니다.")
    private String name;
    private String tel;
}
