package com.myapp.chatbackend.Service;


import com.myapp.chatbackend.Entity.MsgEntity;
import com.myapp.chatbackend.Entity.UserEntity;
import com.myapp.chatbackend.Interface.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserCrudService{

    private final UserRepository userRepository;

    @Autowired
    public UserCrudService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public UserEntity getUserById(int Id){
        System.out.println(Integer.toString(Id));
        return userRepository.getById(Id);
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean updateUser(int Id,String username,String password){
        UserEntity user= userRepository.getById(Id);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public void deleteUser(int Id){
        userRepository.deleteById(Id);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }




}
