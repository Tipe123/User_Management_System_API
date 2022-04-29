package com.user.mngt.system.service;

import com.user.mngt.system.model.User;

import java.util.List;

//it's an interface that contains the methods for business logic
public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUser(Long user_id);

    User updateUser(Long user_id, User user);
}
