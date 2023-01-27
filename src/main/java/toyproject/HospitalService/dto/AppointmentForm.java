package toyproject.HospitalService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentForm {

    private Long hospitalId;
    private String departmentName;
    private Long doctorId;
    private Long patientId;
}
