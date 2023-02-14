package toyproject.HospitalService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toyproject.HospitalService.domain.Doctor;

import java.util.List;

@Repository
public class DoctorRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Doctor doctor) {
        em.persist(doctor);
    }

    public void remove(Long doctorId) {
        Doctor doctor = em.find(Doctor.class, doctorId);
        em.remove(doctor);
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }

    public List<Doctor> findByDepartmentId(Long id) {
        return em.createQuery("select d from Doctor d where d.department.id = :id", Doctor.class)
                .setParameter("id", id)
                .getResultList();
    }
}
