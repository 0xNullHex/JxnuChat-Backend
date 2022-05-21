package com.myapp.chatbackend.Controller;


import com.myapp.chatbackend.Entity.UserEntity;
import com.myapp.chatbackend.Payload.UserRegistrationPayload;
import com.myapp.chatbackend.Service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

//TODO: review Cross Origins
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserCrudController {


    private final UserCrudService userCrudService;

    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserCrudController(UserCrudService userCrudService, PasswordEncoder passwordEncoder) {
        this.userCrudService = userCrudService;
        this.passwordEncoder = passwordEncoder;
    }





    @PostMapping(value = "register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationPayload userBody){
        try {
            UserEntity user = new UserEntity(userBody.getUsername(), passwordEncoder.encode(userBody.getPassword()), userBody.getEmail());
            userCrudService.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Successfully Registered\"}");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Username Or Email already exists\"}");
        }

    }


    @PostMapping("login")
    public String login(){
        return "done";
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
