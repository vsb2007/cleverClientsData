package bgroup.service;

import java.util.List;

import bgroup.model.User;


public interface UserService {

    User findById(int id);

    User findByUserName(String userName);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByUserName(String userName);

    List<User> findAllUsers();

    boolean isUserNameUnique(Integer id, String username);

    boolean changePassword(String id);

    boolean isHasRole(String userName, String role);
}