package de.memozone.usermanagementsystem.services;


import de.memozone.usermanagementsystem.entity.UserEntity;
import de.memozone.usermanagementsystem.model.UserModel;
import de.memozone.usermanagementsystem.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel saveUser(UserModel user) {

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);

        return user;

    }

    @Override
    public List<UserModel> getAllUser() {

        List<UserEntity> userEntityList = userRepository.findAll();

        List<UserModel> users = userEntityList
                .stream()
                .map(userEntity -> new UserModel(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getEmailId()))
                .collect(Collectors.toList());

        return users;
    }

    @Override
    public UserModel getUserById(Long id) {

        UserEntity userEntity = userRepository.findById(id).get();

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);

        return userModel;

    }

    @Override
    public boolean deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
        return true;
    }

    @Override
    public UserModel updateUser(Long id, UserModel user) {

        UserEntity userEntity = userRepository.findById(id).get();

        userEntity.setEmailId(user.getEmailId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);

        return userModel;
    }
}
