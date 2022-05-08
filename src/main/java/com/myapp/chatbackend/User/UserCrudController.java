package com.myapp.chatbackend.User;


import com.myapp.chatbackend.Security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

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

    @GetMapping("user/{id}")
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

    @PutMapping("user/{id}/update")
    public boolean updateUser(@PathVariable("id") int id,@RequestParam("username") String username,@RequestParam("password") String password){
        return userCrudService.updateUser(id , username , passwordEncoder.encode(password));
    }
}
