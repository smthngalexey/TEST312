package springboot.service;

import springboot.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User findUserById(Long id);
    User findUserName (String name);
    List<User> getListUsers();
}
