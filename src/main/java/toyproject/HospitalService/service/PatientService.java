package toyproject.HospitalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.HospitalService.domain.Patient;
import toyproject.HospitalService.repository.PatientRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Long join(Patient patient) {
        patientRepository.save(patient);
        return patient.getId();
    }

    public Patient findOnePatient(Long id) {
        return patientRepository.findOne(id);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }
}
