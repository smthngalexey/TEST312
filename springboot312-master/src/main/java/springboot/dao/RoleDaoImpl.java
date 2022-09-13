package springboot.dao;

import org.springframework.stereotype.Repository;
import springboot.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return  entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }


    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class,id);
    }

    @Override
    public Set<Role> findByIdRoles(List<Integer> roles) {
        return new HashSet<>(entityManager.createQuery("select r from Role r where r.id in :role", Role.class).getResultList());
    }


}
