package toyproject.HospitalService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toyproject.HospitalService.domain.Department;

import java.util.List;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Department department) {
        em.persist(department);
    }

    public Department findOne(Long id) {
        return em.find(Department.class, id);
    }

    public void remove(Long departmentId) {
        Department department = em.find(Department.class, departmentId);
        em.remove(department);
    }

    public List<Department> findAll() {
        return em.createQuery("select d from Department d", Department.class)
                .getResultList();
    }

    public List<Department> findByHospitalId(Long hospitalId) {
        return em.createQuery("select d from Department d where d.hospital.id = :id", Department.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }
}
