package com.myapp.chatbackend.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserCrudService{

    private UserRepository userRepository;

    @Autowired
    public UserCrudService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public UserEntity getUserById(int Id){
        return userRepository.getById(Id);
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findAll().stream()
                .filter(userEntity -> userEntity.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new IllegalStateException(String.format("%s not found",username)));
    }

    public boolean updateUser(int Id,String username,String password){
        UserEntity user= userRepository.getById(Id);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(int Id){
        userRepository.deleteById(Id);
        return true;
    }


}
