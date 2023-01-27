package toyproject.HospitalService.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HospitalForm {

    @NotEmpty(message = "이름을 필수입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
