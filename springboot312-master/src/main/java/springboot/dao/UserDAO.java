package springboot.dao;

import springboot.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User findUserById(Long id);
    User findUserName (String name);
    List<User> getListUsers();
}
