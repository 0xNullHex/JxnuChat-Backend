package com.myapp.chatbackend.Controller;


import com.myapp.chatbackend.Entity.UserEntity;
import com.myapp.chatbackend.Service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/")
public class UserCrudController {


    private UserCrudService userCrudService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserCrudController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @PostMapping("register")
    public String registerUser(@RequestParam("username") String username,@RequestParam("password") String password){
        UserEntity user=new UserEntity(username,passwordEncoder.encode(password));
        userCrudService.saveUser(user);
        return user.toString();

    }

    @GetMapping("user/get/{id}")
    public String getUserById(@PathVariable("id") int ID){
        try{
        return userCrudService.getUserById(ID).toString();
        }
        catch (EntityNotFoundException e){
            return String.format("{" +
                    "\"message\" : " +
                    "\"user with ID %d not found\" }" , ID);
        }
    }

    @GetMapping("user/get/user={username}")
    public String getUserByUsername(@PathVariable("username") String username){
        try{
            return userCrudService.getUserByUsername(username).toString();
        }
        catch (EntityNotFoundException e){
            return String.format("{" +
                    "\"message\" : " +
                    "\"user with username %s not found\" }" , username);
        }
    }

    @PutMapping("user/update/{id}")
    public boolean updateUser(@PathVariable("id") int id,@RequestParam("username") String username,@RequestParam("password") String password){
        return userCrudService.updateUser(id , username , passwordEncoder.encode(password));
    }

    @DeleteMapping("user/delete")
    public boolean deleteUser(@RequestParam("id") int ID){
        return userCrudService.deleteUser(ID);
    }
}