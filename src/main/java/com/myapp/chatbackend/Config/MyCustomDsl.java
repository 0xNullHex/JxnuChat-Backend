package com.myapp.chatbackend.Config;

import com.myapp.chatbackend.Jwt.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        JwtUsernamePasswordAuthenticationFilter filter= new JwtUsernamePasswordAuthenticationFilter(authenticationManager);
        filter.setFilterProcessesUrl("/login");
        http.addFilter(filter);
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
}
