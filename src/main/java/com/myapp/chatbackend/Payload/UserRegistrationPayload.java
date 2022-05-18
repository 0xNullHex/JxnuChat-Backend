package com.myapp.chatbackend.Payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @Getter @Setter @ToString
public class UserRegistrationPayload {

    private String username;
    private String password;
    private String email;

}
