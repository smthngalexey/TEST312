package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.dao.RoleDao;
import springboot.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Override
    public Set<Role> findByIdRoles(List<Integer> roles) {
        return roleDao.findByIdRoles(roles);
    }

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly= true)
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }


    @Override
    @Transactional(readOnly= true)
    public Role findById(long id) {
        return roleDao.findById(id);
    }
}
