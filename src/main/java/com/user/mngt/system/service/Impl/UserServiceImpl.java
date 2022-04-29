package com.user.mngt.system.service.Impl;

import com.user.mngt.system.entity.UserEntity;
import com.user.mngt.system.model.User;
import com.user.mngt.system.repository.UserRepository;
import com.user.mngt.system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


//This class contains methods that implements business logic
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //this function is used to save data to the database table
    @Override
    public User saveUser(User user) {
        //the user variable from the parameters has the values that are structured as the values in the userEntity
        // we create a new user entity that will store the values in the database
        UserEntity userEntity = new UserEntity();

        //This built in function copies values from user to userEntity
        BeanUtils.copyProperties(user,userEntity);

        userRepository.save(userEntity);

        return user;
    }

    //This fuction is used to get all data from the database table UserEntity
    @Override
    public List<User> getAllUsers() {

        //This will store all values from UserEntity table to a new list called userEntities
            List<UserEntity> userEntities = userRepository.findAll();
        //this will store all the values in the users class in a list form
            List<User> users = userEntities.stream().map(userEntity -> new User(
                    userEntity.getId(),
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getEmailId()
            )).collect(Collectors.toList());
            return users;
    }

    //this function gets a single id by user_id which is a primary key
    @Override
    public User getUserById(Long id) {
        //This object will store the value from the database table
        UserEntity userEntity = userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);

        return user;
    }

    @Override
    public boolean deleteUser(Long user_id) {
        UserEntity user = userRepository.findById(user_id).get();
        userRepository.delete(user);
        return  true;
    }

    @Override
    public User updateUser(Long user_id, User user) {
        UserEntity userEntity = userRepository.findById(user_id).get();
            userEntity.setEmailId(user.getEmailId());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
        userRepository.save(userEntity);
        return user;
    }
}
