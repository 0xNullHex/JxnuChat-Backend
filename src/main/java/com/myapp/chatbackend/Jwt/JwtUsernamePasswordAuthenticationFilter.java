package com.myapp.chatbackend.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.chatbackend.Payload.UsernameAndPasswordAuthenticationRequest;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    AuthenticationManager authenticationManager;


    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            System.out.printf("response : %s%n",request.getInputStream().read());
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword());

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            throw new JwtException("Invalid token");
            try {
                response.getWriter().write("{\"message\":\"Invalid Token\"}");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

//      Enter a key here
        String key = "f*$B5H6d$8CcWpg5F58v$$q3*H4cH$mdpazC4^@S$L7#3%2jTQ5wVG%C!XRHC3n%k!snHQ";
        String token= Jwts.builder()
                .setSubject(authResult.getName())
                        .setIssuedAt(new Date())
                                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                                        .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                                                .compact();
        response.addHeader("Authorization","Bearer "+token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\":"+"\""+token+"\",");
        response.getWriter().write("\"username\":"+"\""+authResult.getName()+"\"}");
    }
}
