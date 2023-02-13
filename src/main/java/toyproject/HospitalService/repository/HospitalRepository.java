package toyproject.HospitalService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toyproject.HospitalService.domain.Hospital;

import java.util.List;

@Repository
public class HospitalRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Hospital hospital) {
        em.persist(hospital);
    }

    public void remove(Long id) {
        Hospital hospital = em.find(Hospital.class, id);
        em.remove(hospital);
    }

    public Hospital findOne(Long id) {
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findAll() {
        return em.createQuery("select h from Hospital h", Hospital.class)
                .getResultList();
    }
}
