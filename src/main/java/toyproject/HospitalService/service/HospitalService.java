package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.HospitalService.domain.Hospital;
import toyproject.HospitalService.repository.HospitalRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public Long addHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
        return hospital.getId();
    }

    public Hospital getOneHospital(Long id) {
        return hospitalRepository.findOne(id);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}
