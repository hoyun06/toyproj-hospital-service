package toyproject.HospitalService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toyproject.HospitalService.domain.Patient;

import java.util.List;

@Repository
public class PatientRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Patient patient) {
        em.persist(patient);
    }

    public void remove(Long id) {
        Patient findPatient = em.find(Patient.class, id);
        em.remove(findPatient);
    }

    public Patient findOne(Long id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }

    public List<Patient> findByName(String name) {
        return em.createQuery("select p from Patient p where p.name = :name", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }
}
