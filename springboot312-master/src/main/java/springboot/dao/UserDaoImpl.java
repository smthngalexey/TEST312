package springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import springboot.model.Role;
import springboot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (findUserById(id) != null) {
            entityManager.remove(findUserById(id));
        }
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserName(String name) {
        return entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :name",User.class).getSingleResult();
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
