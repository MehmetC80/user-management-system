package de.memozone.usermanagementsystem.services;

import de.memozone.usermanagementsystem.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel saveUser(UserModel user);


    List<UserModel> getAllUser();

    UserModel getUserById(Long id);

    boolean deleteUser(Long id);

    UserModel updateUser(Long id, UserModel user);
}
