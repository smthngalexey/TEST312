package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import springboot.dao.UserDAO;
import springboot.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserName(String name) {
        return userDao.findUserName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }
}
