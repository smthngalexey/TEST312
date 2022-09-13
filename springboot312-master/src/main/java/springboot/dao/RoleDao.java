package springboot.dao;

import springboot.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    List<Role> getAllRoles ();
    void addRole(Role role);
    Role findById(Long i);

    Set<Role> findByIdRoles(List<Integer> roles);

}
