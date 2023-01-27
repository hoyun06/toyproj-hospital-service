package toyproject.HospitalService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toyproject.HospitalService.domain.Appointment;

import java.util.List;

@Repository
public class AppointmentRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(Appointment appointment) {
        em.persist(appointment);
    }

    public Appointment findOne(Long id) {
        return em.find(Appointment.class, id);
    }

    public List<Appointment> findAll() {
        return em.createQuery("select a from Appointment a", Appointment.class)
                .getResultList();
    }

    public List<Appointment> findByPatient(Long id) {
        return em.createQuery("select a from Appointment a where a.patient.id = :id", Appointment.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Appointment> findByDoctor(Long id) {
        return em.createQuery("select a from Appointment a where a.doctor.id = :id", Appointment.class)
                .setParameter("id", id)
                .getResultList();
    }
}
